package com.wt.common.upload.model;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;

@Table(name="UploadFileT")
public class UploadFile extends BaseEntity<UploadFile>{
    @Id(name = "fileId")
    private String fileId;

    private String fileName;

    private String location;

    private String businessId;

    public String getFileId() {
        return fileId;
    }

    public UploadFile setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public UploadFile setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UploadFile setLocation(String location) {
        this.location = location == null ? null : location.trim();
        return this;
    }

    public String getBusinessId() {
        return businessId;
    }

    public UploadFile setBusinessId(String businessId) {
        this.businessId = businessId;
        return this;
    }
}