package com.hao.testevent;

import com.google.common.collect.Maps;
import com.hao.event.DefaultEventBus;
import com.hao.event.EventBus;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by user on 2016/4/28.
 */
public class TestSubPub {

    EventBus bus;

    @Before
    public void before() {
        bus = new DefaultEventBus();
    }

    @Test
    public void test() {
        LoggingEventListener listener = new LoggingEventListener();
        Map<String,Object> map = Maps.newLinkedHashMap();
        Object o = new Object();
        map.put("hello",o);
        BeanEvent event = new BeanEvent("hello",o,map);
        bus.register(listener);
        bus.publish(event);
        //output
        //2016-04-28 16:56:43.171  INFO   --- [           main] com.hao.testevent.LoggingEventListener   :  bean 'hello' [java.lang.Object@47542153]
    }
}
