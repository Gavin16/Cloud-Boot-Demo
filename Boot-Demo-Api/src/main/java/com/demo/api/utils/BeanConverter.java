package com.demo.api.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
public class BeanConverter {

    /**
     * 类型S 对象属性赋值 T对象
     * @param source
     * @param tClass
     * @param <S>
     * @param <T>
     * @return 若 S为null, 则返回null; 否则 返回T的实例对象
     */
    public static <S,T> T convert(S source, Class<T> tClass){
        if(!Objects.isNull(source)){
            try {
                T tar = tClass.newInstance();
                BeanUtils.copyProperties(source,tar);
                return tar;
            } catch (Exception e) {
                log.error("BeanConverter.convert Bean 转化错误:{}",e);
            }
        }
        return null;
    }


    /**
     * S类型List批量转化为 T类型List
     * @param sourceList
     * @param tClass
     * @param <S>
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <S,T> List<T> convertList(List<S> sourceList, Class<T> tClass){
        if(!CollectionUtils.isEmpty(sourceList)){
            List<T> targetList = Lists.newArrayList();
            try {
                for(S source : sourceList){
                    T tar = tClass.newInstance();
                    BeanUtils.copyProperties(source,tar);
                    targetList.add(tar);
                }
            } catch (Exception e) {
                log.error("BeanConverter.convertList List转化异常:{}",e);
            }
            return targetList;
        }
        return null;
    }

}
