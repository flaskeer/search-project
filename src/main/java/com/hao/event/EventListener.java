package com.hao.event;

/**
 *
 * 事件监听机制
 * Created by user on 2016/4/28.
 */
public interface EventListener {

    /**
     * 判断事件是否可以被处理
     * @param event
     * @return
     */
    boolean accept(Object event);


    /**
     * 处理特定的事件
     * @param event
     */
    void onEvent(Object event);

}
