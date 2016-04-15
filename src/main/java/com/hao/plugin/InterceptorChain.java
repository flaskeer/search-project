package com.hao.plugin;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2016/4/15.
 */
public class InterceptorChain {

    private final List<Interceptor> interceptors = Lists.newArrayList();

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

}
