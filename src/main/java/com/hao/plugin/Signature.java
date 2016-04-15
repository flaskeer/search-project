package com.hao.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by user on 2016/4/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Signature {

    /**
     * 支持拦截的类型
     * @return
     */
    Class<?> type();

    /**
     * 执行的方法名
     * @return
     */
    String method();

    /**
     * 方法参数的类型
     * @return
     */
    Class<?> args();

}
