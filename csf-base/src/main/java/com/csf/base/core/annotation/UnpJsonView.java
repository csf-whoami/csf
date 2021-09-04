package com.csf.base.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ContentNegotiatingViewResolver의 json뷰의 접근제어를 하기 위한 어노테이션
 * 클래스레벨에 적용할 경우 해당 클래스의 모든메소드의 json뷰가 가능하다.
 * 메소드레벨에 적용할 경우 해당 클래스의 메소드만 json뷰가 가능하다.
 * @author khd
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UnpJsonView {

}
