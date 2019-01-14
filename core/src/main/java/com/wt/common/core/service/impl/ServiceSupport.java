package com.wt.common.core.service.impl;

import com.wt.common.core.configuration.PageConfiguration;
import com.wt.common.core.dao.MapperSupport;
import com.wt.common.core.entity.BaseEntity;
import com.wt.common.core.exception.ServiceException;
import com.wt.common.core.handle.QueryHandle;
import com.wt.common.core.result.DataTableResult;
import com.wt.common.core.result.QueryResult;
import com.wt.common.core.service.BaseService;
import com.wt.common.core.util.SpringPropertyUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static com.wt.common.core.util.ReflectUtils.getEntityIdValue;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.service.impl
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/19 下午12:38
 * @Version: v1.0
 */
public abstract class ServiceSupport<T extends BaseEntity, M extends MapperSupport<T>> implements BaseService<T> {

    protected abstract M getMapper();

    @Override
    public void save(T entity) {
        entity.setCreator("099");
        entity.setChangedBy("099");
        entity.setDeleteFlag(false);
        try {
            getMapper().save(entity);
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new ServiceException("数据已存在，请重新录入");
            }
        }
    }

    public void saveBatch(List<T> entities) {
        entities.forEach(entity -> {
            entity.setCreator("999");
            entity.setChangedBy("999");
            entity.setDeleteFlag(false);
        });
        getMapper().saveBatch(entities);
    }

    @Override
    public void deleteLogic(String id) {
        throw new ServiceException("deleteLogic(String id)需要业务端自己实现");
    }

    @Override
    public void deleteLogic(T entity) {
        getMapper().deleteLogicByEntity(entity);
    }

    @Override
    public void delete(String id) {
        throw new ServiceException("delete需要在业务端自己实现");
    }

    @Override
    public void delete(T entity) {
        getMapper().delete((String) getEntityIdValue(entity), entity.getClass());
    }

    @Override
    public void deleteLogicBatch(List<String> ids) {
        throw new ServiceException("deleteLogicBatch需要业务端自己实现");
    }

    @Override
    public void deleteBatchByIds(List<String> ids) {
        throw new ServiceException("deleteBatch需要业务端自己实现");
    }

    @Override
    public void deleteBatchByEntities(List<T> entities) {
        if (!CollectionUtils.isEmpty(entities)) {
            List<String> ids = new ArrayList<>();
            for (int i = 0; i < entities.size(); i++) {
                T entity = entities.get(i);
                ids.add((String) getEntityIdValue(entity));
            }
            getMapper().deleteBatch(ids, entities.get(0).getClass());
        }
    }


    @Override
    public void update(T entity) {
        getMapper().update(entity);
    }

    /**
     * 选择性更新
     *
     * @param entity
     */
    public void updateForSelective(T entity) {
        getMapper().updateForSelective(entity);
    }

    @Override
    public T findById(String id, Class<T> z) {
        return getMapper().selectById(id, z);
    }

    @Override
    public List<T> selectAll() {
        throw new ServiceException("selectAll()方法需要业务端自己实现");
    }

    @Override
    public QueryResult<T> pagingQuery(T entity, QueryHandle queryHandle) {
        QueryResult<T> queryResult = newQueryResult();




        return null;
    }

    @Override
    public List<T> selectByCondition(T entity) {
        return getMapper().selectByCondition(entity);
    }

    @Override
    public List<T> findAll(T entity, QueryHandle queryHandle) {
        return getMapper().findAll(entity, queryHandle);
    }

    private QueryResult<T> newQueryResult(){
        if(StringUtils.isEmpty(SpringPropertyUtils.get("table_choice"))){
            return new DataTableResult<T>();
        }
        return PageConfiguration.create().createQueryResult();
    }

}
