package com.mrpan.wechat.pay.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示不参与签名的字段
 * 标识该注解的字段不参与签名,这里主要考虑到有些常量不需要参与签名的
 */
@Documented
@Target(ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface NotSign {
	
	boolean value() default true;			
}
