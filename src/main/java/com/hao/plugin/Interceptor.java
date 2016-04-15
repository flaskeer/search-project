package com.hao.plugin;

import java.util.Properties;

/**
 *
 * 先实现一个简易的插件机制
 * Created by user on 2016/4/15.
 */
public interface Interceptor {

    Object intercept(Invocation invocation);

    Object plugin(Object target);

    void setProperties(Properties properties);

}
