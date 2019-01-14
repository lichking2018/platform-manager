package com.wt.common.msgCenter.controller;

import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.msgCenter.service.MessageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: sydsxx
 * @Package: com.wt.common.msgCenter.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/6/12 下午4:02
 * @Version: v1.0
 */
@RestController
@RequestMapping("/messageRecord")
public class MessageRecordController extends BaseController{

    @Autowired
    private MessageRecordService service;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public HttpResultEntity<?> getData(){
        return getSuccessResult(null);
    }

}
