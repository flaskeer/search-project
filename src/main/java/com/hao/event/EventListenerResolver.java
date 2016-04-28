package com.hao.event;

import java.util.List;

/**
 *
 *用来处理标有{@link com.hao.event.SubScribe SubScribe}注解的方法
 * Created by user on 2016/4/28.
 */
public interface EventListenerResolver {

    /**
     *
     * @param instance
     * @return
     */
    List<EventListener> getEventListeners(Object instance);

}
