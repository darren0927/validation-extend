package com.darren.validation.ext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author darren
 * @Description:参数约束校验器：MultipleInt 允许参数设置为指定的多个int值
 * @date 2019/1/30 14:52
 */
public class MultipleIntConstraintValidator implements ConstraintValidator<MultipleInt, Integer> {

    /**
     * 注解中设置的允许的默认值
     */
    private int[] values;

    /**
     * 注解中设置的错误提示信息
     */
    private String msg;

    @Override
    public void initialize(MultipleInt constraintAnnotation) {
        this.values = constraintAnnotation.values();
        this.msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(Arrays.asList(values).contains(value)){
            return true;
        }
        /**
         * 返回参数校验失败，并构建错误提示信息
         */
        context.disableDefaultConstraintViolation();
        ConstraintValidatorContext.ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(msg);
        builder.addConstraintViolation();
        return false;
    }
}