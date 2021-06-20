package com.demo.web.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * @className: ValidatorConfig
 * @description: 参数校验配置
 * @version: 1.0
 * @author: minsky
 * @date: 2021/6/20
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 将fail_fast设置为true即可，如果想验证全部，则设置为false或者取消配置即可
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory();
        return factory.getValidator();
    }
}
