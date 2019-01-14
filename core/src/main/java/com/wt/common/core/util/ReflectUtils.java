package com.wt.common.core.util;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.exception.ReflectException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/18 下午9:51
 * @Version: v1.0
 */
public class ReflectUtils {
    /**
     * 获取对象的属性和值，转换为Map的格式输出
     *
     * @return
     * @throws IllegalAccessException
     */
    public static List<Field> getAllFields(Class z) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, z.getDeclaredFields());
        return getParentFields(z.getSuperclass(), fields);
    }

    /**
     * 递归获取所有属性
     *
     * @param parent
     * @param fields
     * @return
     */
    public static List<Field> getParentFields(Class parent, List<Field> fields) {
        if (Object.class.equals(parent)) {
            return fields;
        }
        Collections.addAll(fields, parent.getDeclaredFields());
        return getParentFields(parent.getSuperclass(), fields);
    }

    public static List<Field> getFields(Class z) {
        return Arrays.asList(z.getDeclaredFields());
    }


    /**
     * 获得实体的表名
     *
     * @param entityClass
     * @return
     */
    public static String getEntityTableName(Class entityClass) {
        boolean tableAnnotation = entityClass.isAnnotationPresent(Table.class);
        if (!tableAnnotation) {
            throw new ReflectException(StringUtils.join("获取@Table注解失败：", entityClass.getName(), "没有配置@Table注解"));
        }
        Annotation annotation = entityClass.getAnnotation(Table.class);
        String tableName = ((Table) annotation).name();
        return tableName;
    }

    /**
     * 获取实体的主键值
     *
     * @param entity
     * @return
     */
    public static Object getEntityIdValue(Object entity) {
        try {
            Field field = getEntityIdField(entity.getClass());
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new ReflectException(StringUtils.join("获取entity主键值失败：", entity.getClass().getName()), e);
        }
    }

    /**
     * 获取类的ID字段
     *
     * @param z
     * @return
     */
    public static Field getEntityIdField(Class<?> z) {
        Field[] fields = z.getDeclaredFields();

        Field idField = null;

        for (Field field : fields) {
            boolean isId = field.isAnnotationPresent(Id.class);
            if (isId) {
                idField = field;
                break;
            }
        }

        if (idField == null) {
            throw new ReflectException("entity未定义@Id注解：" + z.getName());
        }
        return idField;
    }

    public static void setIdValue(Object entity) {
        Field idField = getEntityIdField(entity.getClass());

        idField.setAccessible(true);
        try {
            idField.set(entity, CommonUtils.getUUID());
        } catch (IllegalAccessException e) {
            throw new ReflectException("为实体主键设置ID出现异常：" + entity.getClass().getName());
        }

    }


    /**
     * 检测对象的属性是否含有注解
     *
     * @param z
     * @param aClass
     * @return
     */
    public static boolean checkEntityFieldsContainAnnotation(Class z, Class aClass) {
        boolean isContain = false;
        Field[] fields = z.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(aClass)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    /**
     * 检查是否为基本类型or封装类型or日期类型
     *
     * @param field
     */
    public static boolean isBasicType(Field field, Class... z) {
        boolean isBasic = false;
        if (ClassUtils.isPrimitiveOrWrapper(field.getType())) {
            return true;
        }
        for (Class c : z) {
            if (c.isAssignableFrom(field.getType())) {
                isBasic = true;
                break;
            }
        }

        return isBasic;
    }

    /**
     * 将对象的属性和对应的值，转化为Map
     * @param object
     * @return
     */
    public static Map<String, Object> convertObjectToMap(Object object) {
        Class z = object.getClass();
        List<Field> fields = getAllFields(z);
        Map<String, Object> result = new HashMap();
        fields.forEach(e -> {
            e.setAccessible(true);
            try {
                result.put(e.getName(), e.get(object));
            } catch (IllegalAccessException e1) {
                throw new ReflectException("获取对象的属性失败", e1);
            }
        });
        return result;
    }
}
