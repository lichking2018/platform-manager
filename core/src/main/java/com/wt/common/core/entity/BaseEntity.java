package com.wt.common.core.entity;

import com.google.gson.annotations.JsonAdapter;
import com.wt.common.core.adapter.DateAdapter;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.entity
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/19 上午11:32
 * @Version: v1.0
 */
public class BaseEntity<T> implements Serializable {
//创建人
    private String creator;
//  创建时间
    @JsonAdapter(DateAdapter.class)
    private Date createDateTime;
    //修改人
    private String changedBy;
//修改时间
    @JsonAdapter(DateAdapter.class)
    private Date lastUpdate;
//删除标识
    private Boolean deleteFlag;

    public String getCreator() {
        return creator;
    }

    public BaseEntity setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public T setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        return (T)this;
    }
}
