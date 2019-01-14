package com.wt.common.upload.service;

import com.wt.common.core.service.BaseService;
import com.wt.common.upload.exception.FileReadException;
import com.wt.common.upload.exception.UploadException;
import com.wt.common.upload.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 上午9:35
 * @Version: v1.0
 */
public interface UploadFileService extends BaseService<UploadFile>{

    /**
     * 上传图片
     * @param multipartFiles
     * @throws FileReadException
     */
    List<UploadFile> uploadFiles(MultipartFile[] multipartFiles) throws UploadException;
}
