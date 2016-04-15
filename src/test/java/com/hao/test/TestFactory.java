package com.hao.test;

import com.hao.plugin.Interceptor;
import com.hao.plugin.InterceptorChain;

/**
 * Created by user on 2016/4/15.
 */
public class TestFactory {

    private static InterceptorChain interceptorChain = new InterceptorChain();

    public static void add(Interceptor interceptor) {
        interceptorChain.addInterceptor(interceptor);
    }

    public static TT factory(Object target) {
        TT t = (TT) interceptorChain.pluginAll(target);
        return t;
    }

}
