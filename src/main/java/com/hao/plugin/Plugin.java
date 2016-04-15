package com.hao.plugin;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2016/4/15.
 */
public class Plugin implements InvocationHandler{

    private Object target;
    private Interceptor interceptor;
    private Map<Class<?>,Set<Method>> signatureMap;

    public Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    public static Object wrap(Object target,Interceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterface(type, signatureMap);
        if (interfaces.length > 0) {
            return Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Plugin(target, interceptor, signatureMap));
        }
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = signatureMap.get(method.getDeclaringClass());
        if (methods != null && methods.contains(method)) {
            return interceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,args);
    }

    private static Map<Class<?>,Set<Method>> getSignatureMap(Interceptor interceptor) {
        Intercepts interceptAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptAnnotation == null) {
            throw new PluginException("No @Intercepts annoation was found in interceptor " + interceptor.getClass().getName());
        }
        Signature[] sigs = interceptAnnotation.value();
        Map<Class<?>,Set<Method>> signatureMap = Maps.newHashMap();
        for (Signature sig : sigs) {
            Set<Method> methods = signatureMap.get(sig.type());
            if (methods == null) {
                methods = Sets.newHashSet();
                signatureMap.put(sig.type(),methods);
            }
            try {
                Method method = sig.type().getMethod(sig.method(), sig.args());
                methods.add(method);
            } catch (NoSuchMethodException e) {
                throw new PluginException("could not find method on " + sig.type() + " named " + " .cause:" + e,e);
            }
        }
        return signatureMap;
    }

    private static Class<?>[] getAllInterface(Class<?> type,Map<Class<?>,Set<Method>> signatureMap) {
        Set<Class<?>> interfaces = Sets.newHashSet();
        for (Class<?> c : type.getInterfaces()) {
            if (signatureMap.containsKey(c)) {
                interfaces.add(c);
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class[interfaces.size()]);
    }
}
