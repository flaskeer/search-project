package com.hao.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 标注在方法上，作为事件消费者  参数里必须指明事件类型
 * Created by user on 2016/4/28.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface SubScribe {
}
