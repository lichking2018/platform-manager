package com.wt.common.core.util;


import com.wt.common.core.exception.DaoException;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description: md5加密工具
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/15 上午11:24
 * @Version: v1.0
 */
public class MD5Utils {
    public static String md5(String str){
        //确定计算方法
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DaoException("MD5加密异常",e);
        }
    }

    public static boolean check(String target,String base){
        if(md5(target).equals(base)){
            return true;
        }
        return false;
    }
}
