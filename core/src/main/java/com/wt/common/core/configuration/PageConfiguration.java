package com.wt.common.core.configuration;

import com.wt.common.core.configuration.implement.DataTableService;
import com.wt.common.core.util.SpringContextUtils;
import com.wt.common.core.util.SpringPropertyUtils;

import static com.wt.common.core.configuration.PageConvert.DataTable;
import static com.wt.common.core.configuration.PageConvert.EasayUi;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.configuration
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午4:31
 * @Version: v1.0
 */
public class PageConfiguration {
    private static PageConvert pageConvert;


    public static PageConvert create(){
        //【知识点】，优化的一种方式
        if(pageConvert!=null){
            return pageConvert;
        }
        String tableType = SpringPropertyUtils.get("table_choice");

        switch (tableType){
            case DataTable:
                pageConvert = new DataTableService();
                break;
            case EasayUi:
                break;
            default:
                pageConvert = (PageConvert) SpringContextUtils.getBean(SpringPropertyUtils.get("table_choice")
                        +"Service");
        }

        return pageConvert;
    }

}
