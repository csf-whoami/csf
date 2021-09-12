package com.csf.whoami.base.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class ObjectUtil {

    public static void removeEmptyField(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        setNullForEmptyFields(object, fields);
        if(object.getClass().getSuperclass() != null){
            Field[] supperClassFields = object.getClass().getSuperclass().getDeclaredFields();
            setNullForEmptyFields(object, supperClassFields);
        }
    }

    private static void setNullForEmptyFields(Object object, Field[] fields) {
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if ("".equals(field.get(object))) {
                    field.set(object, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            String str = (String) object;
            return str.length() == 0;
        }
        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            return collection.size() == 0;
        }
        if (object.getClass().isArray()) {
            try {
                if (Array.getLength(object) == 0) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static boolean isNull(Object vo){
        ObjectUtil.removeEmptyField(vo);
        boolean f = true;
        try{
            Object obj = vo;
            for (Field field : obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value = field.get(obj);
                if(value != null){
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public static String createQuery(Object vo){
        ObjectUtil.removeEmptyField(vo);
        String query = "";
        try{
            Object obj = vo;
            for (Field field : obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value = field.get(obj);
                if(value != null){
                    if(query.equals("")){
                        query += " where " + field.getName() + " like '%" + value + "%'";
                    } else {
                        query += " and " + field.getName() + " like '%" + value + "%'";
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return query;
    }
}
