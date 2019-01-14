package com.wt.common.upload.controller;

import com.wt.common.core.controller.BaseController;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.upload.exception.UploadException;
import com.wt.common.upload.model.UploadFile;
import com.wt.common.upload.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.controller
 * @Description: 文件上传
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 上午9:42
 * @Version: v1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController extends BaseController{

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 上传图片
     * @param imageFile
     * @return
     * @throws UploadException
     */
    @RequestMapping(value="/uploadImage",method = RequestMethod.POST)
    public HttpResultEntity<?> uploadImage(MultipartFile[] imageFile) throws UploadException {
        List<UploadFile> uploadFileList = uploadFileService.uploadFiles(imageFile);
        return getSuccessResult(uploadFileList);
    }

    /**
     * 删除图片
     * @param fileId
     * @return
     */
    @RequestMapping(value="/deleteFile/{fileId}",method = RequestMethod.DELETE)
    public HttpResultEntity<?> deleteFile(@PathVariable("fileId") String fileId) {
        uploadFileService.deleteLogic(fileId);
        return getSuccessResult();
    }
}
