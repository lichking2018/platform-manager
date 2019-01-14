package com.wt.common.data.redis.util;

import java.util.Map;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.data.redis
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/11 下午6:12
 * @Version: v1.0
 */
public class HashOperationUtils extends BaseRedisOperation{
    public static void put(Object key,Object hashKey,Object hashValue){
        getRedisTemplate().opsForHash().put(key,hashKey,hashValue);
    }

    public static <HK,HV> void putAll(Object key, Map<HK,HV> hValueMap){
        getRedisTemplate().opsForHash().putAll(key,hValueMap);
    }

    public static Long delete(Object key,Object... hashKeys){
        return getRedisTemplate().opsForHash().delete(key,hashKeys);
    }

    public static Object get(Object key,Object hashKey){
        return getRedisTemplate().opsForHash().get(key,hashKey);
    }

    public static void increment(Object key,Object hashkey,long step){
        getRedisTemplate().opsForHash().increment(key,hashkey,step);
    }

}
