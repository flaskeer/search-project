package com.hao.util;

import com.google.common.collect.Lists;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by user on 2016/4/28.
 */
public class ClassUtil {

    public static List<Method> getAnnotatedMethod(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = Lists.newArrayList();
        Class<?> clazz = type;
        while(!Object.class.equals(clazz)) {
            Method[] currentClassMethods = clazz.getDeclaredMethods();
            for (Method method : currentClassMethods) {
                if (annotation == null || method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

}
