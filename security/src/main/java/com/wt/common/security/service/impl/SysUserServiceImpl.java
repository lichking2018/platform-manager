package com.wt.common.security.service.impl;

import com.google.gson.JsonObject;
import com.wt.common.core.service.impl.ServiceSupport;
import com.wt.common.core.util.MD5Utils;
import com.wt.common.email.enums.EmailType;
import com.wt.common.email.util.EmailSendUtils;
import com.wt.common.msgCenter.util.MessageSendUtils;
import com.wt.common.security.dao.SysUserMapper;
import com.wt.common.security.exception.AccountOrPasswordErrorException;
import com.wt.common.security.exception.BaseSecurityException;
import com.wt.common.security.exception.DulplicateUserException;
import com.wt.common.security.model.SysUser;
import com.wt.common.security.service.SysUserService;
import com.wt.common.upload.model.UploadFile;
import com.wt.common.upload.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.security.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 上午9:05
 * @Version: v1.0
 */
@Service
@Validated
public class SysUserServiceImpl extends ServiceSupport<SysUser, SysUserMapper> implements SysUserService {
    Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);


    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    protected SysUserMapper getMapper() {
        return this.sysUserMapper;
    }

    @Override
    public void delete(String id) {
        getMapper().delete(id, SysUser.class);
    }

    @Override
    public void deleteLogic(String id) {
        getMapper().deleteLogicByEntity(new SysUser().setUserId(id));
        deleteFromRedis("sysUser"+id);
    }

    @Override
    public void deleteLogicBatch(List<String> ids) {
        getMapper().deleteLogicBatch(ids, SysUser.class);
    }

    @Override
    public void deleteBatchByIds(List<String> ids) {
        getMapper().deleteBatch(ids, SysUser.class);
    }

    @Override
    @Cacheable(cacheNames="sysUser",key = "#root.targetClass+#root.methodName+#id")
    public SysUser findById(String id, Class<SysUser> z) {
        SysUser sysUser = new SysUser();
        sysUser = super.findById(id, z);
        List<UploadFile> uploadFiles = uploadFileService.selectByCondition(new UploadFile().setBusinessId(id).setDeleteFlag(false));
        sysUser.setAvatars(uploadFiles);
        return sysUser;
    }


    @Override
    public List<SysUser> selectAll() {
        return getMapper().selectAll(SysUser.class);
    }

    @Override
    public void save(SysUser entity) {
//        EmailBody emailBody = this.createEmailBody(entity);
        JsonObject param = createEmailParam(entity);

        MessageSendUtils.send(entity.getMail(),"001",param);

        super.save(entity.setPassword(MD5Utils.md5(entity.getPassword())));
        List<UploadFile> avatars = entity.getAvatars();
        //处理图片
        this.updateAvatar(entity);
        this.saveToRedis(entity);
    }

    private JsonObject createEmailParam(SysUser sysUser){
//        String message = String.format("尊敬的用户：%s，您的账户已经创建。【账号】%s，【密码】%s，【注册邮箱】%s"
//        ,sysUser.getUserName(),sysUser.getAccount(),sysUser.getPassword(),sysUser.getMail());
//        String subject = "【沈阳都市信息网】账户创建通知";
//        EmailBody emailBody = new EmailBody();
//        emailBody.setSubject(subject);
//        emailBody.setMessage(message);
//        emailBody.setTo(sysUser.getMail());
//        emailBody.setEmailType(EmailType.SIMPLE);

        JsonObject param = new JsonObject();
        param.addProperty("userName",sysUser.getUserName());
        param.addProperty("userAccount",sysUser.getAccount());
        param.addProperty("password",sysUser.getPassword());
        param.addProperty(EmailSendUtils.EMAIL_TYPE,EmailType.SIMPLE.toString());
        param.addProperty(EmailSendUtils.SUBJECT,"账号创建通知");
        return param;
    }


    private void saveToRedis(SysUser entity){
        String key = "sysUser"+entity.getUserId();
//        hashRedisUtil.saveObject(key,entity);
    }

    private void deleteFromRedis(String key){
    }
    @Override
    public void updateForSelective(SysUser entity) {
        super.updateForSelective(entity);
        this.updateAvatar(entity);
        this.saveToRedis(entity);
    }

    /**
     * 补充头像的businessId信息
     *
     * @param entity
     */
    private void updateAvatar(SysUser entity) {
        List<UploadFile> avatars = entity.getAvatars();
        if(!CollectionUtils.isEmpty(avatars)){
            //处理图片
            avatars.forEach(avatar -> {
                avatar.setBusinessId(entity.getUserId());
                uploadFileService.updateForSelective(avatar);
            });
        }
    }


    @Override
    public SysUser login(String account, String password) throws BaseSecurityException {
        List<SysUser> sysUsers = this.selectByCondition(new SysUser().setAccount(account).setPassword(MD5Utils.md5(password)));

        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new AccountOrPasswordErrorException();
        }

        if(sysUsers.size()>1){
            throw new DulplicateUserException();
        }

        return sysUsers.get(0);
    }
}
