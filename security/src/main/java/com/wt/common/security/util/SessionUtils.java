package com.wt.common.security.util;

import com.wt.common.core.util.WebContextUtils;
import com.wt.common.security.model.SysUser;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.security.util
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/20 下午2:56
 * @Version: v1.0
 */
public class SessionUtils {
    private static final String LOGIN_USER="session_user";
//    private static final String LOGIN_USER_ID="session_user_id";
//    private static final String LOGIN_USER_NAME="session_user_name";


    public static void setLoginUser(SysUser currentLoginUser){
        WebContextUtils.getSession().setAttribute(LOGIN_USER,currentLoginUser);
    }

    public static SysUser getLoginUser(){
        return (SysUser)WebContextUtils.getSession().getAttribute(LOGIN_USER);
    }

    public static String getLoginUserId(){
        return getLoginUser().getUserId();
    }

    public static String getLoginUserName(){
        return getLoginUser().getUserName();
    }

}
