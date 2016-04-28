package com.hao.event;

import com.google.common.collect.Lists;
import com.hao.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2016/4/28.
 */
public class AnnoationEventListenerResolver implements EventListenerResolver {


    private Class<? extends Annotation> annotationClass;

    public AnnoationEventListenerResolver() {
        this.annotationClass = SubScribe.class;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public AnnoationEventListenerResolver setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
        return this;
    }

    @Override
    public List<EventListener> getEventListeners(Object instance) {
        if (instance == null) {
            return Collections.emptyList();
        }
        List<Method> methods = ClassUtil.getAnnotatedMethod(instance.getClass(), getAnnotationClass());
        if (methods == null || methods.isEmpty()) {
            return Collections.emptyList();
        }
        List<EventListener> listeners = Lists.newArrayListWithCapacity(methods.size());
        for (Method method : methods) {
            listeners.add(new SingleArgumentEventListener(instance,method));
        }
        return listeners;

    }
}


