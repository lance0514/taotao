package com.taotao.jedis;

import redis.clients.jedis.JedisCluster;

public class JedisClientPool implements JedisClient {


    private JedisCluster jedisCluster;


    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Boolean exists(String key) {
        return null;
    }

    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String value) {
        return null;
    }

    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hdel(String key, String... field) {
        return null;
    }
}
