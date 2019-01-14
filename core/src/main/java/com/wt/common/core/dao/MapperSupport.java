package com.wt.common.core.dao;

import com.wt.common.core.handle.QueryHandle;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.dao
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午8:15
 * @Version: v1.0
 */
public interface MapperSupport<T> {
    String ID = "id";
    String IDS = "ids";
    String ENTITY = "entity";
    String ENTITIES = "entities";
    String ENTITYCLASS = "entityClass";
    String DELETEFLAG = "deleteFlag";
    String QueryHandler = "queryHandler";
    String Data = "data";

    @InsertProvider(type = SqlProvider.class, method = "save")
    void save(@Param(ENTITY) T entity);

    @InsertProvider(type = SqlProvider.class, method = "saveBatch")
    void saveBatch(@Param(ENTITIES) List<T> entities);

    //    @UpdateProvider(type = SqlProvider.class,method = "deleteLogic")
//    void deleteLogic(@Param(ID) Integer id,@Param(ENTITYCLASS) Class<?> z);
    @UpdateProvider(type = SqlProvider.class, method = "deleteLogicByEntity")
    void deleteLogicByEntity(@Param(ENTITY) T entity);

    @DeleteProvider(type = SqlProvider.class, method = "delete")
    void delete(@Param(ID) String id, @Param(ENTITYCLASS) Class<?> z);

    @UpdateProvider(type = SqlProvider.class, method = "deleteLogicBatch")
    void deleteLogicBatch(@Param(IDS) List<String> ids, @Param(ENTITYCLASS) Class<?> z);

    @DeleteProvider(type = SqlProvider.class, method = "deleteBatch")
    void deleteBatch(@Param(IDS) List<String> ids, @Param(ENTITYCLASS) Class<?> z);


    @UpdateProvider(type = SqlProvider.class, method = "update")
    void update(@Param(ENTITY) T entity);

    @UpdateProvider(type = SqlProvider.class, method = "updateForSelective")
    void updateForSelective(@Param(ENTITY) T entity);


    @SelectProvider(type = SqlProvider.class, method = "selectById")
    T selectById(@Param(ID) String id, @Param(ENTITYCLASS) Class<?> z);

    @SelectProvider(type = SqlProvider.class, method = "selectAll")
    List<T> selectAll(@Param(ENTITYCLASS) Class<?> z);

    @SelectProvider(type = SqlProvider.class, method = "selectByCondition")
    List<T> selectByCondition(@Param(ENTITY) T entity);

    @SelectProvider(type = SqlProvider.class, method = "findAllBySubQuery")
    List<T> findAll(@Param(ENTITY) T entity, @Param(QueryHandler) QueryHandle queryHelper);

//    List<T> queryDataByPage(@Param("current") Integer current,@Param("pageSize") Integer pageSize);

    @SelectProvider(type = SqlProvider.class,method = "pagingQuery")
    List<T> pagingQuery(@Param(ENTITY) T entity,@Param(Data) Map<String,Object> data);
}
