package com.hao.event;

/**
 *
 *
 * Created by user on 2016/4/28.
 */
public interface EventBus {

    /**
     * 发布事件
     * @param event
     */
    void publish(Object event);

    /**
     * 注册事件
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 取消注册事件
     * @param subscriber
     */
    void unregister(Object subscriber);
}
