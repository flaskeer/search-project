package com.hao.testevent;

import com.hao.event.Event;

import java.util.Map;

/**
 * Created by user on 2016/4/28.
 */
public class BeanEvent extends Event{

    private String beanName;
    private Object bean;
    private Map<String,Object> beanContext;
    /**
     * Constructs a prototypical Event.
     *
     * @param bean The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public BeanEvent(String beanName,Object bean,Map<String,Object> beanContext) {
        super(bean);
        this.bean = bean;
        this.beanName = beanName;
        this.beanContext = beanContext;
    }

    public String getBeanName() {
        return beanName;
    }

    public Object getBean() {
        return bean;
    }

    public Map<String, Object> getBeanContext() {
        return beanContext;
    }
}
