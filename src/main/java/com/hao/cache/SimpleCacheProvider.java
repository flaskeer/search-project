package com.hao.cache;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 2016/4/15.
 */
public class SimpleCacheProvider implements CacheProvider{

    private static SimpleCacheProvider instance = new SimpleCacheProvider();

    private static ConcurrentHashMap<String,Serializable> container = new ConcurrentHashMap<>();

    public static SimpleCacheProvider getInstance() {
        return instance;
    }

    @Override
    public void put(String key, Serializable cacheObject) {
        container.put(key,cacheObject);
    }

    @Override
    public Serializable get(String key) {
        return container.get(key);
    }

    @Override
    public void remove(String key) {
        container.remove(key);
    }

    @Override
    public void clear() {
        container.clear();
    }
}
