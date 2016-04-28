package com.hao.event;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * 处理一个接收单一参数的方法
 * Created by user on 2016/4/28.
 */
public class SingleArgumentEventListener implements TypedEventListener{

    private Object target;
    private Method method;

    public SingleArgumentEventListener(Object target, Method method) {
        this.target = target;
        this.method = method;
        getMethodArgument(method);
        assertPublicMethod(method);
    }

    @Override
    public Class getEventType() {
        return getMethodArgument(method);
    }

    @Override
    public boolean accept(Object event) {
        return event != null && getEventType().isInstance(event);
    }

    @Override
    public void onEvent(Object event) {
        Method method = getMethod();
        try {
            method.invoke(target,event);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to invoke event handler method [" + method + "].",e);
        }
    }

    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 用来判断传入的参数是否是一个
     * @param method
     * @return
     */
    protected Class getMethodArgument(Method method) {
        Class[] paramTypes = method.getParameterTypes();
        if (paramTypes.length != 1) {
            String msg = "Event handler methods must accept a single argument";
            throw new IllegalArgumentException(msg);
        }
        return paramTypes[0];
    }

    protected void assertPublicMethod(Method method) {
        int modifiers = method.getModifiers();
        if (!Modifier.isPublic(modifiers)) {
            throw new IllegalArgumentException("Event handler method [" + method + "] must be public.");
        }
    }
}
