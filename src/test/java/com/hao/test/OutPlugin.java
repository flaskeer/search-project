package com.hao.test;

import com.hao.plugin.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by user on 2016/4/15.
 */
@Intercepts(@Signature(type = TT.class,method = "test",args = {Object.class}))
public class OutPlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("intercept");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("plugin");
        if (target instanceof TT) {
            return Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("setProperties");
    }
}
