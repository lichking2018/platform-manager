package com.wt.common.data.redis.util;

import com.wt.common.core.util.SpringContextUtils;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.data.redis.util
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/12 下午5:14
 * @Version: v1.0
 */
public class BaseRedisOperation {
    private static RedisTemplate redisTemplate;

    static RedisTemplate getRedisTemplate(){
        if(redisTemplate==null){
            redisTemplate = (RedisTemplate) SpringContextUtils.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
