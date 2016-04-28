package com.hao.event;

import java.util.EventObject;

/**
 *
 * Created by user on 2016/4/28.
 */
public abstract class Event extends EventObject{

    private final long timestamp;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
