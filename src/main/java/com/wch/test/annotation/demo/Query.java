package com.wch.test.annotation.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过获取注解信息，以反射的形式生成sql语句
 */
public class Query {

    private static final Logger log = LoggerFactory.getLogger(Query.class);

    private static String query(User user) {
        StringBuffer sb = new StringBuffer();

        // 加载类对象
        Class c = user.getClass();

        // 获取表名
        boolean isExistTableName = c.isAnnotationPresent(Table.class);
        if (!isExistTableName) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();

        sb.append("SELECT * FROM ").append(tableName).append(" WHERE 1 = 1");

        // 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 获取字段名
            boolean isExistColumnName = field.isAnnotationPresent(Column.class);
            if (!isExistColumnName) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();

            // 获取字段名
            String fieldName = field.getName();
            // 获取get方法名
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            // 获取字段值
            Object fieldValue;
            try {
                Method method = c.getMethod(getMethodName);
                fieldValue = method.invoke(user);
            } catch (Exception e) {
                log.info(e.getMessage());
                return null;
            }

            if (null != fieldValue) {
                sb.append(" AND ").append(columnName).append(" = ");
                if (fieldValue instanceof String) {
                    sb.append("'").append(fieldValue).append("'");
                } else {
                    sb.append(fieldValue);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);
        String query1 = Query.query(user1);

        User user2 = new User();
        user2.setName("wch");
        String query2 = Query.query(user2);

        User user3 = new User();
        user3.setAge(21);
        String query3 = Query.query(user3);

        User user4 = new User();
        user4.setId(1);
        user4.setName("wch");
        user4.setAge(21);
        String query4 = Query.query(user4);

        log.info("Query 1: {}", query1);
        log.info("Query 2: {}", query2);
        log.info("Query 3: {}", query3);
        log.info("Query 4: {}", query4);
    }
}
