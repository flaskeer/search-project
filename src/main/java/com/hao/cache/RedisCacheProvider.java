package com.hao.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by user on 2016/4/15.
 */
public class RedisCacheProvider implements CacheProvider{

    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;

    @Override
    public void put(String key, Serializable cacheObject) {
        redisTemplate.execute(new RedisCallback<Serializable>() {
            @Override
            public Serializable doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<Serializable> value = (RedisSerializer<Serializable>) redisTemplate.getValueSerializer();
                redisConnection.set(redisTemplate.getStringSerializer().serialize(key),value.serialize(cacheObject));
                return null;
            }
        });
    }

    @Override
    public Serializable get(String key) {
        redisTemplate.execute(new RedisCallback<Serializable>() {
            @Override
            public Serializable doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
                if (redisConnection.exists(redisKey)) {
                    byte[] value = redisConnection.get(redisKey);
                    Serializable valueSerial = (Serializable) redisTemplate.getValueSerializer().deserialize(value);
                    return valueSerial;
                }
                return null;
            }
        });
        return null;
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void clear() {

    }
}
