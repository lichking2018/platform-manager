package com.wt.common.msgCenter.util;

import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.msgCenter.util
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/22 下午3:52
 * @Version: v1.0
 */
public class MessageRegularUtils {
    public static String replaceDoubleBraces(String content, JsonObject param){
        String regular = "(?<=\\{\\{)[^\\}\\}]+";
        Pattern pattern = Pattern.compile(regular);

        Matcher matcher = pattern.matcher(content);

        while(matcher.find()){
            String reg = matcher.group();
            if(null==param.get(reg)){
                throw new IllegalArgumentException(String.format("模板解析错误，入参中没有包含【%s】",reg));
            }
            content = content.replace("{{"+reg+"}}",param.get(reg).toString());
        }

        return content;
    }


    public static void main(String args[]){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name","包小兵");
        String content = replaceDoubleBraces("尊敬的用户：{{name}}",jsonObject);

        System.out.println(content);
    }

}
