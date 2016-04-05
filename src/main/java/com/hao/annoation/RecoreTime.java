package com.hao.annoation;

import java.lang.annotation.*;

/**
 * Created by user on 2016/3/30.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecoreTime {
    String desc() default "";
}
