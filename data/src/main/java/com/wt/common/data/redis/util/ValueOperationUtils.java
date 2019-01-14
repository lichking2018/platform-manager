package com.wt.common.data.redis.util;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.data.redis
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/11 下午6:13
 * @Version: v1.0
 */
public class ValueOperationUtils extends BaseRedisOperation{

    public static void set(Object key,Object value){
        getRedisTemplate().opsForValue().set(key,value);
    }

    public static Object get(Object key){
        return getRedisTemplate().opsForValue().get(key);
    }

    public static void set(Object key,Object value,long num){
        getRedisTemplate().opsForValue().set(key,value,num);
    }

    public static void delete(Object key){
        getRedisTemplate().delete(key);
    }

    public static void expire(Object key,long time){
        getRedisTemplate().expire(key,time, TimeUnit.SECONDS);
    }
}
