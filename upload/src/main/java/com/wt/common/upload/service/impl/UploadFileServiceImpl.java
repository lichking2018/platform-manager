package com.wt.common.upload.service.impl;

import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.upload.dao.UploadFileMapper;
import com.wt.common.upload.exception.FileReadException;
import com.wt.common.upload.exception.UploadException;
import com.wt.common.upload.handler.FileHandler;
import com.wt.common.upload.model.UploadFile;
import com.wt.common.upload.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.upload.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/6 上午9:36
 * @Version: v1.0
 */

@Service
public class UploadFileServiceImpl extends ServiceSupport<UploadFile, UploadFileMapper> implements UploadFileService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private FileHandler fileHandler;

    @Override
    protected UploadFileMapper getMapper() {
        return this.uploadFileMapper;
    }


    @Override
    public List<UploadFile> uploadFiles(MultipartFile[] multipartFiles) throws UploadException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                byte[] bytes = multipartFile.getBytes();
                String suffix = multipartFile.getContentType().split("/")[1];
                UploadFile uploadFile = fileHandler.writeFileSystem(bytes, suffix);
                uploadFiles.add(uploadFile);
                this.save(uploadFile);
            } catch (IOException e) {
                throw new FileReadException();
            }
        }
        return uploadFiles;
    }

    @Override
    public void deleteLogic(String id) {
        getMapper().deleteLogicByEntity(new UploadFile().setFileId(id));
    }
}
