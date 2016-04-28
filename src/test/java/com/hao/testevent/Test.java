package com.hao.testevent;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by user on 2016/4/28.
 */
public class Test {

    @org.junit.Test
    public void test() {
        LoggingEventListener listener = new LoggingEventListener();
        Map<String,Object> map = Maps.newLinkedHashMap();
        map.put("hello",new Object());
        BeanEvent event = new BeanEvent("hello",new Object(),map);
        listener.onEvent(event);
        //output
        //2016-04-28 16:56:43.171  INFO   --- [           main] com.hao.testevent.LoggingEventListener   :  bean 'hello' [java.lang.Object@47542153]
    }
}
