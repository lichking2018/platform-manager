package com.wt.common.core.service;

import com.wt.common.core.handle.QueryHandle;
import com.wt.common.core.result.QueryResult;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.service
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/19 下午12:36
 * @Version: v1.0
 */
@Validated
public interface BaseService<T> {

    /**
     * 单条保存
     * @param entity
     */
    void save(T entity);

    /**
     * 批量新增
     * @param entities
     */
    void saveBatch(List<T> entities);

    /**
     * 逻辑删除
     * @param id
     */
    void deleteLogic(String id);

    /**
     * 逻辑删除
     * @param entity
     */
    void deleteLogic(T entity);

    /**
     * 物理删除
     * @param id
     */
    void delete(@NotEmpty(message = "主键不能为空") String id);

    /**
     * 物理删除
     * @param entity
     */
    void delete(T entity);


    /**
     * 批量逻辑删除
     * @param ids
     */
    void deleteLogicBatch(List<String> ids);

    /**
     * 批量物理删除
     * @param ids
     */
    void deleteBatchByIds(List<String> ids);

    /**
     * 批量物理删除
     * @param objs
     */
    void deleteBatchByEntities(List<T> objs);


    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    /**
     * 选择性更新
     * @param entity
     */
    void updateForSelective(T entity);

    /**
     * 根据主键查询记录
     * @param id 主键
     * @return
     */
    T findById(String id, Class<T> z);

    /**
     * 关联查询
     * @param entity 查询实体
     * @param queryHelper 查询帮助类
     * @return
     */
    List<T> findAll(T entity, QueryHandle queryHandle);

    QueryResult<T> pagingQuery(T entity, QueryHandle queryHandle);

    /**
     * 查询所有记录
     * @return
     */
    List<T> selectAll();

    /**
     * 根据条件查找
     * @param entity
     * @return
     */
    List<T> selectByCondition(T entity);

}
