package com.wt.common.security.model;

import com.google.gson.annotations.JsonAdapter;
import com.wt.common.core.adapter.DateAdapter;
import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;
import com.wt.common.upload.model.UploadFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name="SysUserT")
public class SysUser extends BaseEntity<SysUser>{

//    private static final long serialVersionUID = -5146971959215053601L;

    @Id(name = "userId")
    private String userId;

    private String password;

    private String account;

    private String userName;

    private Boolean inUse;

    private String mail;

    @JsonAdapter(DateAdapter.class)
    @NotNull(message = "邮箱不能为空")
    private Date birthday;

    private List<UploadFile> avatars;

    public String getUserId() {
        return userId;
    }

    public SysUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getAccount() {
        return account;
    }

    public SysUser setAccount(String account) {
        this.account = account == null ? null : account.trim();
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SysUser setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
        return this;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public SysUser setInUse(Boolean inUse) {
        this.inUse = inUse;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public SysUser setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public SysUser setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public List<UploadFile> getAvatars() {
        return avatars;
    }

    public SysUser setAvatars(List<UploadFile> avatars) {
        this.avatars = avatars;
        return this;
    }
}