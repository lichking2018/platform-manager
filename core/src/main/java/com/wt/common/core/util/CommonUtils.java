package com.wt.common.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/8 下午9:43
 * @Version: v1.0
 */
public class CommonUtils {

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 日期转化为字符串
     *
     * @param pattern
     * @return
     */
    public static String dateFormat(String pattern, Date date) {
        org.springframework.util.Assert.notNull(pattern, "pattern 不能为null");
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String result = sdf.format(date);
        return result;
    }

}
