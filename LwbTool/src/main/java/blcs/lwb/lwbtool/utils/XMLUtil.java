package blcs.lwb.lwbtool.utils;

import android.text.TextUtils;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwb on 2017/12/26.
 * TODO XML文件工具类，包含：将xml文件解析成实体集合、获取xml标签值、将标签值解析成实体集合
 * 1、XML文件解析成实体,不涉及到标签的属性值。
 * 2、获取xml字符串标签中的属性值
 * 3、获取Xml文件中的属性值
 *
 */

public class XMLUtil {

    private XMLUtil(){}

    /**
     * 1、XML文件解析成实体,不涉及到标签的属性值。
     * @param xml   xml字符串文件
     * @param clazz     对应实体的class文件
     * @param tagEntity
     * 开始解析实体的标签，例如下面的实例中就是student<br>
     * < person ><br>
     *      < student ><br>
     *              < name >Lucy< /name ><br>
     *              < age >21< /age ><br>
     *      < /student ><br>
     * < /person ><br>
     * @return      返回解析的对应实体文件
     */
    public static<T> List<T> xmlToObject(String xml, Class<T> clazz, String tagEntity){
        List<T> list = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        try {
            xmlPullParser.setInput(inputStream, "utf-8");
            Field[] fields = clazz.getDeclaredFields();
            int type = xmlPullParser.getEventType();
            String lastTag = "";
            T t = null;
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<T>();
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = xmlPullParser.getName();
                        if(tagEntity.equals(tagName)){
                            t = clazz.newInstance();
                            lastTag = tagEntity;
                        }else if(tagEntity.equals(lastTag)){
                            String textValue = xmlPullParser.nextText();
                            String fieldName = xmlPullParser.getName();
                            for(Field field : fields){
                                ReflectUtil.setFieldValue(t,field,fieldName,textValue);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xmlPullParser.getName();
                        if(tagEntity.equals(tagName)){
                            list.add(t);
                            lastTag = "";
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 2、获取xml字符串标签中的属性值
     * @param xml   xml字符串
     * @param clazz     转换成对应的实体
     * @param tagName   实体对应xml字符串的起始标签,如下面实例中的person标签<br>
     * < person name="Lucy" age="12"><br>
     *      < student ><br>
     *              < name >Lucy< /name ><br>
     *              < age >21< /age ><br>
     *      < /student ><br>
     * < /person ><br>
     * @return  返回属性值组成的List对象集合。
     */
    public static<T> List<T> attributeToObject(String xml, Class<T> clazz, String tagName){
        if(TextUtils.isEmpty(tagName))return null;
        List<T> list = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        try {
            xmlPullParser.setInput(inputStream, "utf-8");
            int type = xmlPullParser.getEventType();
            T t = null;
            while(type != XmlPullParser.END_DOCUMENT){
                switch(type){
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<T>();
                        break;
                    case XmlPullParser.START_TAG:
                        if(tagName.equals(xmlPullParser.getName())){
                            t = clazz.newInstance();
                            Field[] fields = clazz.getDeclaredFields();
                            for(Field field : fields){
                                String fieldName = field.getName();
                                for(int index = 0;index < xmlPullParser.getAttributeCount();index++){
                                    if(fieldName.equals(xmlPullParser.getAttributeName(index))){
                                        ReflectUtil.setFieldValue(t,field,fieldName,xmlPullParser.getAttributeValue(index));
                                    }
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(tagName.equals(xmlPullParser.getName())){
                            list.add(t);
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }
                type = xmlPullParser.next();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;

    }

    /**
     * 3、获取Xml文件中的属性值
     * @param xml   xml文件字符串
     * @param tagName       标签名称
     * @param attributeName     属性名称
     * @return  返回获取的值，或者null
     */
    public static String getTagAttribute(String xml, String tagName, String attributeName){
        if(TextUtils.isEmpty(tagName) || TextUtils.isEmpty(attributeName)){
            throw new IllegalArgumentException("请填写标签名称或属性名称");
        }
        XmlPullParser xmlPullParser = Xml.newPullParser();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        try {
            xmlPullParser.setInput(inputStream, "utf-8");
            int type = xmlPullParser.getEventType();
            while(type != XmlPullParser.END_DOCUMENT){
                switch(type){
                    case XmlPullParser.START_TAG:
                        if(tagName.equals(xmlPullParser.getName())){
                            for(int i=0; i < xmlPullParser.getAttributeCount();i++){
                                if(attributeName.equals(xmlPullParser.getAttributeName(i))){
                                    return xmlPullParser.getAttributeValue(i);
                                }
                            }
                        }
                        break;
                }
                type = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
