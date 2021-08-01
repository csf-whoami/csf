package com.csf.whoami.database.adapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.csf.base.utilities.StringUtils;
import com.csf.whoami.aop.Property;

public class BaseAdapter {

    /**
     * Copy value from object to new Class.
     * @param <T>
     * @param <U>
     * @param inputObj
     * @param clazz
     * @return
     */
    public static <T, U> U copyFromObject(T inputObj, Class<U> clazz) {

        try {
            U obj = clazz.newInstance();
            Field[] fields = inputObj.getClass().getDeclaredFields();
            for(Field item : fields) {
                Object value = runGetter(item, inputObj);

                Field fieldName = null;
                Field[] declareFields = obj.getClass().getDeclaredFields();
                for(Field field : declareFields) {
                    if (item.getName().equals(field.getName())) {
                        fieldName = field;
                    }
                }

                if (fieldName != null) {
                    // Set value to properties.
                    Object type = convertType(value, item, fieldName);
                    runSetter(fieldName, obj, type);
                } else {
                    Field[] destinationFields = obj.getClass().getDeclaredFields();
                    for(Field desItem : destinationFields) {
                        Annotation[] desAnnotations = desItem.getAnnotations();
                        String propertiesName = propertyName(desAnnotations);
                        if (StringUtils.isNullOrEmpty(propertiesName)) {
                            propertiesName = desItem.getName();
                        }
                        if (!StringUtils.isNullOrEmpty(propertiesName) && item.getName().equals(propertiesName)) {
                            runSetter(desItem, obj, value);
                        }
                    }
                }
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    private static Object convertType(Object value, Field fromType, Field toType) {
        if (fromType.getType().getName().equals(toType.getType().getName())) {
            return value;
        }

        if (!fromType.getType().isPrimitive()) {
            return value;
        }

        String midleConvert = null;
        if (byte.class.isAssignableFrom(value.getClass()) || value instanceof Byte
                || short.class.isAssignableFrom(value.getClass()) || value instanceof Short
                || int.class.isAssignableFrom(value.getClass()) || value instanceof Integer 
                || long.class.isAssignableFrom(value.getClass()) || value instanceof Long
                || char.class.isAssignableFrom(value.getClass())
                || float.class.isAssignableFrom(value.getClass()) || value instanceof Float
                || double.class.isAssignableFrom(value.getClass()) || value instanceof Double) {
            midleConvert = String.valueOf(value);
//            return midleConvert;
        } else if (value instanceof Date) {
            // Convert date.
        }

        // Convert Object to primitive.
        return value;
    }

    /**
     * Getter method.
     * @param field
     * @param inputObject
     * @return
     */
    private static <T> Object runGetter(Field field, T inputObject) {
        for (Method method : inputObject.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))
                    && method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                try {
                    return method.invoke(inputObject);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Could not determine method: " + method.getName());
                }
            }
        }

        return null;
    }

    private static <T> void runSetter(Field field, T inputObject, Object value) {
        for (Method method : inputObject.getClass().getMethods()) {
            if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))
                    && method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                try {
                    method.invoke(inputObject, value);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Could not determine method: " + method.getName());
                }
            }
        }
    }

    /**
     * Check field name in class.
     * 
     * @author tuandang
     * @param clazz
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unused")
    private static Field getFieldByName(Class<?> clazz, String fieldName) {
        Class<?> tmpClass = clazz;
        do {
            try {
                Field field = tmpClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                tmpClass = tmpClass.getSuperclass();
            }
        } while (tmpClass != null);

        return null;
    }

//    @SuppressWarnings("unused")
//    private static Annotation[] findClassAnnotation(Class<?> clazz) {
//        return clazz.getAnnotations();
//    }

    @SuppressWarnings("unused")
    private static Annotation[] findMethodAnnotation(Class<?> clazz, String methodName) {

        Annotation[] annotations = null;
        try {
            Class<?>[] params = null;
            Method method = clazz.getDeclaredMethod(methodName, params);
            if (method != null) {
                annotations = method.getAnnotations();
            }
        } catch (SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return annotations;
    }

    private static String propertyName(Annotation[] ann) {
        if (ann == null) { return null; }
        for (Annotation a : ann) {
            if (a instanceof Property) {
                Property prop = (Property) a;
                return prop.name();
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
    private static Annotation[] findFieldAnnotation(Class<?> clazz, String fieldName) {
        Annotation[] annotations = null;
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field != null) {
                annotations = field.getAnnotations();
            }
        } catch (SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return annotations;
    }
}
