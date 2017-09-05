package com.taotao.sso.jedis;

public interface JedisClient {

    //set
    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    //自动删除
    Long expire(String key, int seconds);

    //时间
    Long ttl(String key);

    Long incr(String key);

    //hash
    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String... field);


}
