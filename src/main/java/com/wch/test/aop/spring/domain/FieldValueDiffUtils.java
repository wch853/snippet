package com.wch.test.aop.spring.domain;

import com.wch.test.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象属性值变更比较工具
 * （通过Json字符串是否equal判断变更）
 */
public class FieldValueDiffUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldValueDiffUtils.class);

    /**
     * 自定义JpaRepository中根据id获取对象的方法为 findOne（需要重写）
     * （继承自org.springframework.data.repository.CrudRepository）
     */
    private static final String ATTAIN_OLD_OBJECT_METHOD = "findOne";

    /**
     * getter方法前缀
     */
    private static final String GETTER_METHOD_PREFIX = "get";

    /**
     * 变更项默认值
     */
    private static final String DEFAULT_ITEM_VALUE = "";

    /**
     * 是否过滤Bean中属性值为null的情况
     */
    private static final boolean IS_FILTER_NULL_BEAN_VALUE = Boolean.TRUE;

    /**
     * 通过反射执行自定义JpaRepository#findOne方法，获取对应id的记录
     *
     * @param target 目标对象
     * @param id     记录id
     * @return 记录
     */
    public static Object getOldObject(Object target, Long id) {
        Object obj = null;
        try {
            Method method = target.getClass().getMethod(ATTAIN_OLD_OBJECT_METHOD, Long.class);
            obj = method.invoke(target, id);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("get old object exception: {}", e.getMessage());
        }
        return obj;
    }

    /**
     * 获取指定属性的属性值
     *
     * @param object    目标Bean
     * @param fieldName 属性名称
     * @return 属性值
     */
    private static Object getFieldValue(Object object, String fieldName) {
        Object fieldValue = null;
        if (null != object) {
            Class<?> clazz = object.getClass();
            try {
                // 获取该属性getter方法
                Method method = clazz.getMethod(GETTER_METHOD_PREFIX.
                        concat(fieldName.substring(0, 1).toUpperCase()).
                        concat(fieldName.substring(1)));
                // 通过反射获取属性值
                fieldValue = method.invoke(object);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                LOGGER.error("get field value exception: {}", e.getMessage());
            }
        }
        return fieldValue;
    }

    /**
     * 获取Bean中的属性和值
     *
     * @param object       目标Bean
     * @param isFilterNull {@code true} 过滤值为null的属性，{@code false} 不过滤
     * @return field-value map
     */
    private static Map<String, String> getBeanFieldValueMap(Object object, boolean isFilterNull) {
        Map<String, String> fieldValueMap = new HashMap<>();
        if (null != object) {
            // 获取该Bean的类对象
            Class<?> clazz = object.getClass();
            // 该类的属性集合
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 属性名
                String fieldName = field.getName();
                // 属性值
                Object fieldValue = getFieldValue(object, fieldName);
                if (!isFilterNull || null != fieldValue) {
                    fieldValueMap.put(fieldName, JsonUtils.toJsonString(fieldValue));
                }
            }
        }
        return fieldValueMap;
    }

    /**
     * 新增操作，新值整体作为变更项
     *
     * @param object object
     * @return 变更项集合
     */
    public static List<ChangeItem> getInsertChangeItems(Object object) {
        ChangeItem changeItem = new ChangeItem();
        changeItem.setField(DEFAULT_ITEM_VALUE);
        changeItem.setOldValue(DEFAULT_ITEM_VALUE);
        changeItem.setNewValue(JsonUtils.toJsonString(object));
        return Collections.singletonList(changeItem);
    }

    /**
     * 修改操作，比较新原值，获取变更项集合
     *
     * @param oldObject 原值
     * @param newObject 新值
     * @return 变更项集合
     */
    public static List<ChangeItem> getUpdateChangeItems(Object oldObject, Object newObject) {
        List<ChangeItem> changeItems = new ArrayList<>();
        // 获取类对象
        Map<String, String> oldFieldValueMap = getBeanFieldValueMap(oldObject, IS_FILTER_NULL_BEAN_VALUE);
        Map<String, String> newFieldValueMap = getBeanFieldValueMap(newObject, IS_FILTER_NULL_BEAN_VALUE);

        ChangeItem changeItem;
        for (Map.Entry<String, String> newFieldValueEntry : newFieldValueMap.entrySet()) {
            String fieldName = newFieldValueEntry.getKey();
            String newFieldValue = newFieldValueEntry.getValue();
            String oldFieldValue = oldFieldValueMap.get(fieldName);
            if (null == oldFieldValue || !oldFieldValue.equals(newFieldValue)) {
                // 原值不包含此属性或属性值不同，认定为变更项
                changeItem = new ChangeItem();
                changeItem.setField(fieldName);
                changeItem.setOldValue(oldFieldValue);
                changeItem.setNewValue(newFieldValue);
                changeItems.add(changeItem);
            }
        }
        return changeItems;
    }

    /**
     * 删除操作，原值整体作为变更项
     *
     * @param object 原值
     * @return 变更项集合
     */
    public static List<ChangeItem> getDeleteChangeItems(Object object) {
        ChangeItem changeItem = new ChangeItem();
        changeItem.setField(DEFAULT_ITEM_VALUE);
        changeItem.setOldValue(JsonUtils.toJsonString(object));
        changeItem.setNewValue(DEFAULT_ITEM_VALUE);
        return Collections.singletonList(changeItem);
    }
}
