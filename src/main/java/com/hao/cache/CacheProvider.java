package com.hao.cache;

import java.io.Serializable;

/**
 * 缓存相关
 * Created by user on 2016/4/15.
 */
public interface CacheProvider {

    void put(String key, Serializable cacheObject);

    Serializable get(String key);

    void remove(String key);

    //清除所有的缓存
    void clear();

}
