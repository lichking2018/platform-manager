package com.wt.common.core.dao;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.OneToOne;
import com.wt.common.core.exception.DaoException;
import com.wt.common.core.ibatis.SQL;
import com.wt.common.core.util.ReflectUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wt.common.core.util.ReflectUtils.*;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.dao
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午9:11
 * @Version: v1.0
 */
public class SqlProvider {

    private String tableName;

    private Class<?> entityClass;

    private Object entity;

    private String sql;

    private List entities;

    private Field idField;


    /**
     * 单挑插入
     *
     * @param param
     * @return
     */
    public String save(Map<String, Object> param) {
        entity = param.get(MapperSupport.ENTITY);
        setIdValue(entity);
        entityClass = entity.getClass();
        tableName = getEntityTableName(entityClass);
        List<Field> fields = getAllFields(entityClass);
        sql = new SQL() {{
            INSERT_INTO(tableName);
            for (Field item : fields) {
                item.setAccessible(true);
                //过滤掉非基本类型、日期类型、一对一
                if (!ReflectUtils.isBasicType(item, String.class, Date.class) && !item.isAnnotationPresent(OneToOne.class)) {
                    continue;
                }
                //处理一对一映射
                if (item.isAnnotationPresent(OneToOne.class)) {
                    try {
                        Annotation annotation = item.getAnnotation(OneToOne.class);
                        OneToOne oneToOne = (OneToOne) annotation;
                        INTO_COLUMNS(oneToOne.column());
                        Field field = item.getType().getDeclaredField(oneToOne.property());
                        field.setAccessible(true);
                        INTO_VALUES(StringUtils.join("#{", MapperSupport.ENTITY, ".", item.getName(), ".", field.getName(), "}"));
                    } catch (NoSuchFieldException e) {
                        throw new DaoException("OneToOne注解中，指定的property不存在", e);
                    }
                } else {
                    INTO_COLUMNS(item.getName());
                    INTO_VALUES(StringUtils.join("#{", MapperSupport.ENTITY, ".", item.getName(), "}"));
                }
            }
        }}.toString();
        return sql;
    }

    /**
     * 批量插入
     *
     * @param param
     * @return
     */
    public String saveBatch(Map<String, Object> param) {
        entities = (List) param.get(MapperSupport.ENTITIES);

        entities.forEach(entity -> {
            setIdValue(entity);
        });

        if (CollectionUtils.isEmpty(entities)) {
            throw new DaoException("批量插入失败，参数无内容");
        }

        entity = entities.get(0);
        entityClass = entity.getClass();
        String tableName = getEntityTableName(entityClass);

        List<Field> fields = getAllFields(entityClass);

        sql = new SQL() {{
            BATCH_INSERT_INTO(tableName);
            fields.forEach(field -> {
                BATCH_INTO_COLUMNS(field.getName());
            });
            for (int i = 0; i < entities.size(); i++) {
                List<String> valueItem = new ArrayList<>();
                for (int j = 0; j < fields.size(); j++) {
                    valueItem.add(StringUtils.join("#{", MapperSupport.ENTITIES, "[", i, "]", ".", fields.get(j).getName(), "}"));
                }
                BATCH_INTO_VALUES(valueItem);
            }
        }}.toString();

        return sql;
    }

    /**
     * 逻辑删除
     *
     * @param param
     * @return
     */
    public String deleteLogic(Map<String, Object> param) {
        return null;
    }

    /**
     * 物理删除
     *
     * @param param
     * @return
     */
    public String delete(Map<String, Object> param) {
        entityClass = (Class) param.get(MapperSupport.ENTITYCLASS);
        String tableName = getEntityTableName(entityClass);
        if (!checkEntityFieldsContainAnnotation(entityClass, Id.class)) {
            throw new DaoException(StringUtils.join("entity没有定义@Id注解：", entityClass.getName()));
        }

        Field idField = getEntityIdField(entityClass);
        String columName = idField.getName();

        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE(StringUtils.join(columName, "=#{", MapperSupport.ID, "}"));
        }}.toString();
    }

    public String deleteBatch(Map<String, Object> param) {
        entityClass = (Class) param.get(MapperSupport.ENTITYCLASS);
        tableName = getEntityTableName(entityClass);
        List<String> ids = (List) param.get(MapperSupport.IDS);
        Field idField = getEntityIdField(entityClass);
        sql = new SQL() {{
            BATCH_DELETE_FROM(tableName);
            WHERE_COLUME(idField.getName());
            for (int i = 0; i < ids.size(); i++) {
                IN_VALUE(StringUtils.join("#{", MapperSupport.IDS, "[", i, "]", "}"));
            }
        }}.toString();
        return sql;
    }

    public String deleteLogicByEntity(Map<String, Object> param) {
        entityClass = param.get(MapperSupport.ENTITY).getClass();
        tableName = getEntityTableName(entityClass);
        idField = getEntityIdField(entityClass);

        sql = new SQL() {{
            UPDATE(tableName);
            SET(StringUtils.join(MapperSupport.DELETEFLAG, "=", "true"));
            WHERE(StringUtils.join(idField.getName(), "=#{", MapperSupport.ENTITY, ".", idField.getName(), "}"));
        }}.toString();
        return sql;
    }

    public String update(Map<String, Object> param) {
        entityClass = param.get(MapperSupport.ENTITY).getClass();
        tableName = getEntityTableName(entityClass);
        idField = getEntityIdField(entityClass);

        sql = new SQL() {{
            UPDATE(tableName);
            List<Field> fields = getAllFields(entityClass);
            for (Field field : fields) {
                field.setAccessible(true);
                //过滤非（基本类型、一对一映射）
                if (!isBasicType(field, String.class, Date.class) && !field.isAnnotationPresent(OneToOne.class)) {
                    continue;
                }
                //过滤主键
                if (idField.getName().equals(field.getName())) {
                    continue;
                }
                //处理一对一映射
                if (field.isAnnotationPresent(OneToOne.class)) {
                    Annotation annotation = field.getAnnotation(OneToOne.class);
                    OneToOne oneToOne = (OneToOne) annotation;
                    String column = oneToOne.column();
                    String property = oneToOne.property();
                    SET(StringUtils.join(column, "=#{", MapperSupport.ENTITY, ".", field.getName(), ".", property, "}"));
                } else {
                    SET(StringUtils.join(field.getName(), "=#{", MapperSupport.ENTITY, ".", field.getName(), "}"));
                }
            }
            WHERE(StringUtils.join(idField.getName(), "=#{", MapperSupport.ENTITY, ".", idField.getName(), "}"));
        }}.toString();
        return sql;
    }

    public String updateForSelective(Map<String, Object> param) {
        entity = param.get(MapperSupport.ENTITY);
        entityClass = entity.getClass();
        tableName = getEntityTableName(entityClass);
        idField = getEntityIdField(entityClass);

        sql = new SQL() {{
            UPDATE(tableName);
            List<Field> fields = getFields(entityClass);
            String fieldName = "";
            try {
                for (Field field : fields) {
                    field.setAccessible(true);

                    //过滤非（基本类型、一对一映射）
                    if (!isBasicType(field, String.class, Date.class) && !field.isAnnotationPresent(OneToOne.class)) {
                        continue;
                    }

                    //过滤主键
                    if (idField.getName().equals(field.getName()) || !ObjectUtils.allNotNull(field.get(entity))) {
                        continue;
                    }

//                    fieldName = field.getName();
//                    if (idField == field || !ObjectUtils.allNotNull(field.get(entity))) {
//                        continue;
//                    }

                    //处理一对一映射
                    if (field.isAnnotationPresent(OneToOne.class)) {
                        Annotation annotation = field.getAnnotation(OneToOne.class);
                        OneToOne oneToOne = (OneToOne) annotation;
                        String column = oneToOne.column();
                        String property = oneToOne.property();
                        SET(StringUtils.join(column, "=#{", MapperSupport.ENTITY, ".", field.getName(), ".", property, "}"));
                    } else {
                        SET(StringUtils.join(field.getName(), "=#{", MapperSupport.ENTITY, ".", field.getName(), "}"));
                    }

//                    SET(StringUtils.join(field.getName(), "=#{", MapperSupport.ENTITY, ".", field.getName(), "}"));
                }
            } catch (IllegalAccessException e) {
                throw new DaoException(StringUtils.join("获取entity属性失败：", entityClass.getName(), ".", fieldName), e);
            }

            WHERE(StringUtils.join(idField.getName(), "=#{", MapperSupport.ENTITY, ".", idField.getName(), "}"));
        }}.toString();

        return sql;
    }

    public String selectById(Map<String, Object> param) {
        entityClass = (Class) param.get(MapperSupport.ENTITYCLASS);
        tableName = getEntityTableName(entityClass);
        idField = getEntityIdField(entityClass);

        sql = new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE(StringUtils.join(idField.getName(), "=#{", MapperSupport.ID, "}"));
        }}.toString();
        return sql;
    }


    public String deleteLogicBatch(Map<String, Object> param) {
        entityClass = (Class) param.get(MapperSupport.ENTITYCLASS);
        List<String> ids = (List) param.get(MapperSupport.IDS);
        if (CollectionUtils.isEmpty(ids)) {
            throw new DaoException("没有选择任何记录");
        }
        tableName = getEntityTableName(entityClass);
        idField = getEntityIdField(entityClass);

        sql = new SQL() {{
            BATCH_UPDATE(tableName);
            SET(StringUtils.join(MapperSupport.DELETEFLAG, "=", "true"));
            WHERE(idField.getName());
            for (int i = 0; i < ids.size(); i++) {
                IN_VALUE(StringUtils.join("#{", MapperSupport.IDS, "[", i, "]", "}"));
            }
        }}.toString();
        return sql;
    }

    public String selectAll(Map<String, Object> param) {
        entityClass = (Class) param.get(MapperSupport.ENTITYCLASS);
        tableName = getEntityTableName(entityClass);
        sql = new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE(StringUtils.join(MapperSupport.DELETEFLAG, "=false"));
        }}.toString();
        return sql;
    }

    public String selectByCondition(Map<String, Object> param) {
        entity = param.get(MapperSupport.ENTITY);
        tableName = getEntityTableName(entity.getClass());

        sql = new SQL() {{
            SELECT("*");
            FROM(tableName);

            List<Field> fields = getBasicTypeAndNotNullColumns(entity.getClass());
            for (Field field : fields) {
                WHERE(StringUtils.join(field.getName(), "=", "#{", MapperSupport.ENTITY, ".", field.getName(), "}"));
            }
        }}.toString();

        return sql;
    }

    public String pagingQuery(Map<String,Object> param){

    }

    public String findAllBySubQuery(Map<String, Object> param) {
        entity = param.get(MapperSupport.ENTITY);
//        QueryHelper queryHelper = (QueryHelper) param.get(MapperSupport.QueryHandle);

        tableName = getEntityTableName(entity.getClass());
//        sql = new SQL() {{
//            if (queryHelper.getDistinct()) {
//                SELECT_DISTINCT("t.*");
//            } else {
//                SELECT("t.*");
//            }
//
//            for (String columns : queryHelper.getQueryColumns()) {
//                SELECT(columns);
//            }
//            FROM(tableName + " t");
//            for (QueryHelper.JoinHandler joinHandler : queryHelper.getJoinHandlers()) {
//                switch (joinHandler.getJoinType()) {
//                    case INNER_JOIN:
//                        JOIN(joinHandler.getJoinCondition());
//                        break;
//                    case LEFT_JOIN:
//                        LEFT_OUTER_JOIN(joinHandler.getJoinCondition());
//                        break;
//                    case RIGHT_JOIN:
//                        RIGHT_OUTER_JOIN(joinHandler.getJoinCondition());
//                }
//            }
//
//            List<Field> fields = getBasicTypeAndNotNullColumns(entity.getClass());
//
//            for (Field field : fields) {
//                WHERE(StringUtils.join(field.getName(), "=", "#{", MapperSupport.ENTITY, ".", field.getName(), "}"));
//            }
//
//            for (String whereSql : queryHelper.getCustomWhereSql()) {
//                WHERE(whereSql);
//            }
//            for (String orderBy : queryHelper.getOrderBy()) {
//                ORDER_BY(orderBy);
//            }
//        }}.toString();
        return sql;
    }

    private List<Field> getBasicTypeAndNotNullColumns(Class z) {
        List<Field> fields = new ArrayList<>();
        String fieldName = "";
        try {
            for (Field field : getAllFields(z)) {
                field.setAccessible(true);
                fieldName = field.getName();
                if (!isBasicType(field, String.class, Date.class) || null == field.get(entity)) {
                    continue;
                }
                fields.add(field);
            }
        } catch (IllegalAccessException e) {
            throw new DaoException("条件查询失败，获取属性失败:" + entity.getClass().getName() + "." + fieldName, e);
        }
        return fields;
    }




}

