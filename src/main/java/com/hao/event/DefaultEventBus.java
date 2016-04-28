package com.hao.event;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 *
 * Created by user on 2016/4/28.
 */
public class DefaultEventBus implements EventBus{

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEventBus.class);

    private EventListenerResolver eventListenerResolver;

    private Map<Object,SubScription> registry;

    private Lock registryReadLock;

    private Lock registryWriteLock;

    public DefaultEventBus() {
        this.registry = Maps.newLinkedHashMap();
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        this.registryReadLock = rw.readLock();
        this.registryWriteLock = rw.writeLock();
        this.eventListenerResolver = new AnnoationEventListenerResolver();
    }

    public EventListenerResolver getEventListenerResolver() {
        return eventListenerResolver;
    }

    public DefaultEventBus setEventListenerResolver(EventListenerResolver eventListenerResolver) {
        this.eventListenerResolver = eventListenerResolver;
        return this;
    }

    @Override
    public void publish(Object event) {
        if (event == null) {
            LOGGER.info("Received null event for publishing .Ignoring and returning");
            return;
        }
        registryReadLock.lock();
        try {
            for (SubScription subScription : registry.values()) {
                subScription.onEvent(event);
            }
        } finally {
            registryReadLock.unlock();
        }

    }

    @Override
    public void register(Object instance) {
        if (instance == null) {
            LOGGER.info("Received null instance for event listener registration. Ignoring registration request.");
            return;
        }
        unregister(instance);
        List<EventListener> eventListeners = getEventListenerResolver().getEventListeners(instance);
        if (eventListeners == null || eventListeners.isEmpty()) {
            LOGGER.warn("Unable to resolve event listeners for subscriber instance {}.Ignoring registration requests.",instance);
            return;
        }
        SubScription subScription = new SubScription(eventListeners);
        registryWriteLock.lock();
        try {
            registry.put(instance,subScription);
        } finally {
            registryWriteLock.unlock();
        }
    }

    @Override
    public void unregister(Object instance) {
        if (instance == null) {
            return;
        }
        registryWriteLock.lock();
        try {
            registry.remove(instance);
        } finally {
            registryWriteLock.unlock();
        }
    }

    private class SubScription {
        private List<EventListener> listeners;

        public SubScription(List<EventListener> listeners) {
            this.listeners = listeners;
        }

        public void onEvent(Object event) {
            Set<Object> delivered = Sets.newHashSet();
            for (EventListener listener : listeners) {
                Object target = listener;
                if (listener instanceof SingleArgumentEventListener) {
                    SingleArgumentEventListener singleListener = (SingleArgumentEventListener) listener;
                    target = singleListener.getTarget();
                }
                if (listener.accept(event) && !delivered.contains(target)) {
                    listener.onEvent(event);
                    delivered.add(target);
                }

            }
        }
    }
}
