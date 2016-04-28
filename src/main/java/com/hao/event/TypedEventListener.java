package com.hao.event;

/**
 * Created by user on 2016/4/28.
 */
public interface TypedEventListener extends EventListener{

    Class getEventType();

}
