package blcs.lwb.lwbtool.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Created by lwb on 2017/12/26.
 * TODO ReflectUtil反射工具类
 * 1、设置字段值
 * 2、把字段名称第一个字母换成大写
 * 3、根据字段名称获取指定Field字段
 * 4、根据字段名称获取指定的Field
 * 5、判断该字段是否为FieldName对应字段
 *
 */

public class ReflectUtil {
    private ReflectUtil(){}

    /**
     * 设置字段值
     * @param t     对应实体
     * @param field     字段
     * @param fieldName     字段名称
     * @param value         字段值
     */
    public static<T> void setFieldValue(T t, Field field, String fieldName, String value){
        String name = field.getName();
        //判断该字段是否和目标字段相同
        if (!fieldName.equals(name)) {
            return;
        }
        //获取字段的类型
        Type type = field.getType();
        //获取字段的修饰符号码
        int typeCode = field.getModifiers();
        //获取字段类型的名称
        String typeName = type.toString();
        try {
            switch (typeName) {
                case "class java.lang.String":
                    if (Modifier.isPublic(typeCode)) {
                        field.set(t, value);
                    } else {
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName), String.class);
                        method.invoke(t, value);
                    }
                    break;
                case "double":
                    if(Modifier.isPublic(typeCode)){
                        field.setDouble(t, Double.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName),double.class);
                        method.invoke(t, Double.valueOf(value));
                    }
                    break;
                case "int":
                    if(Modifier.isPublic(typeCode)){
                        field.setInt(t, Integer.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName),int.class);
                        method.invoke(t, Integer.valueOf(value));
                    }
                    break;
                case "float":
                    if(Modifier.isPublic(typeCode)){
                        field.setFloat(t, Float.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName), float.class);
                        method.invoke(t, Float.valueOf(value));
                    }
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2、把字段名称第一个字母换成大写
     * @param fieldName     字段名称
     * @throws Exception    异常处理
     */
    private static String getMethodName(String fieldName) throws Exception {
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char)items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 3、根据字段名称获取指定Field字段
     * @param clazz        实体的字节码文件
     * @param filedName        字段的名称
     * @return 返回对应的字符按Field或者返回null
     */
    public static Field getField(Class<?> clazz, String filedName){
        if (clazz == null || TextUtils.isEmpty(filedName)) {
            throw new IllegalArgumentException("params is illegal");
        }
        Field[] fields = clazz.getDeclaredFields();
        return getFieldByName(fields, filedName);
    }

    /**
     * 4、根据字段名称获取指定的Field
     * @param fields   字段集合
     * @param fieldName     字段名称
     * @return 返回对应的Field字段或者返回null
     */
    public static Field getFieldByName(Field[] fields, String fieldName){
        if (fields == null || fields.length == 0 || TextUtils.isEmpty(fieldName)) {
            throw new IllegalArgumentException("params is illegal");
        }
        for (Field field : fields) {
            String name = field.getName();
            //判断该字段是否和目标字段相同
            if (fieldName.equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 5、判断该字段是否为FieldName对应字段
     * @param field        Field字段
     * @param fieldName        目标字段
     * @return 是，返回true；否，返回false
     */
    public static boolean isFiledWithName(Field field, String fieldName){
        if(field == null || TextUtils.isEmpty(fieldName)){
            throw new IllegalArgumentException("params is illegal");
        }
        if (fieldName.equals(field.getName())) {
            return true;
        }
        return false;
    }
}
