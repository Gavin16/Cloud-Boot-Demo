package com.demo.api.annotation.aspect;

import com.alibaba.fastjson.JSON;
import com.demo.api.annotation.ParamValidator;
import com.demo.api.annotation.UseParamValidator;
import com.demo.api.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
@Aspect
@Slf4j
public class ParamValidateAspect {
    


    @Around("@annotation(com.demo.api.annotation.ParamValidator)")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        log.info("进入ParamCheck注解切面方法...");
        Object[] args = point.getArgs();
        log.info("方法执行参数:【{}】",JSON.toJSONString(args));

        // 从传参中获取需要验证的对象
        Object paramDto = getRelatedParamDto(args);
        if(Objects.isNull(paramDto)){
            return point.proceed(args);
        }

        // 从当前方法中获取注解信息
        ParamValidator currAnnotation = getParamValidator(point);
        if(!Objects.isNull(currAnnotation)){
            Set<String> set = getFieldNameSetToValidate(currAnnotation);

            if(args != null && args.length > 0){
                Object arg = args[0];
                Class<?> aClass = arg.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();

                for(Field field : declaredFields){
                    field.setAccessible(true);
                    if(set.contains(field.getName())){
                        Class<?> type = field.getType();
                        checkParamByType(paramDto,field,type);
                    }
                }
            }
        }
        return point.proceed(args);
    }



    /**
     * 从方法传参列表中获带验证的DTO对象
     * @param args
     * @return
     */
    private Object getRelatedParamDto(Object[] args) {
        if(args != null && args.length > 0 ){
            for(Object obj : args){
                UseParamValidator annotation = obj.getClass().getAnnotation(UseParamValidator.class);
                if(!Objects.isNull(annotation)){
                    return obj;
                }
            }
        }
        return null;
    }


    /**
     * 根据指定类型校验参数
     * 数值型: int,long,short 及对应包装类型 , BigDecimal
     * 布尔型: boolean
     * 字符串: String
     * 字符串数组: String[]
     * 日期：Date
     *
     * @param obj
     * @param field
     * @param type
     */
    private void checkParamByType(Object obj,Field field, Class<?> type) throws Exception{
        Object fieldObj = field.get(obj);
        // 按类型做校验
        if(fieldObj instanceof String){
            String strValue = (String) fieldObj;
            if(StringUtils.isNotEmpty(strValue)){
                return;
            }
        }else{
            if(!Objects.isNull(fieldObj)){
                return;
            }
        }
        throw new ServiceException("500",field.getName() + "传参不能为空");
    }



    private boolean isNumerical(Object object){
        boolean isInt = object instanceof Integer;
        boolean isLong = object instanceof Long;
        boolean isShort = object instanceof Short;
        boolean isDecimal = object instanceof BigDecimal;

        return isInt || isLong || isShort || isDecimal;
    }


    /**
     * 注解中指定的属性获取待验证属性列表
     * @param currAnnotation
     * @return
     */
    private Set<String> getFieldNameSetToValidate(ParamValidator currAnnotation){
        String methodName = currAnnotation.method();
        String[] fields = currAnnotation.fields();

        Set<String> fieldSet = new HashSet<>();
        if(fields.length > 0){
            log.info("当前方法待验证参数为:{}",JSON.toJSONString(fields));
            for(String field : fields){
                fieldSet.add(field);
            }
        }else {
            // 根据 method 名从全局配置文件中读取校验参数
            Map<String, String> env = System.getenv();
            String fieldString = env.get(methodName);
            log.info("读取到{} 方法需验证参数配置为:{}", methodName,fieldString);
            if(StringUtils.isNotEmpty(fieldString)){
                String[] split = fieldString.split(",");
                for(String field : split){
                    fieldSet.add(field);
                }
            }
        }
        return fieldSet;
    }



    /**
     * 通过注解修饰目标获取类信息 及 方法签名, 然后直接由类信息获取指定方法(Method)
     * 最后通过Method 对象获取到代码中的注解信息
     * @param point
     * @return
     */
    private ParamValidator getParamValidator(ProceedingJoinPoint point){
        // 获取类 及 方法签名
        Class<?> clazz = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature)point.getSignature();

        ParamValidator annotation = null;
        try {
            // 通过类获取方法信息
            Method currentMethod = clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            annotation = currentMethod.getAnnotation(ParamValidator.class);
        } catch (Exception e) {
            log.error("获取方法上注解信息失败:",e);
        }
        return annotation;
    }

}
