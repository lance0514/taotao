package com.taotao.sso.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster jedisClusterl;

    @Override
    public String set(String key, String value) {
        return jedisClusterl.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisClusterl.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedisClusterl.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisClusterl.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedisClusterl.ttl(key);
    }

    @Override
    public Long incr(String key) {
        return jedisClusterl.incr(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisClusterl.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisClusterl.hget(key,field);
    }

    @Override
    public Long hdel(String key, String... field) {
        return jedisClusterl.hdel(key, field);
    }
}
