package com.winning.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.winning.exception.MyException;

/**
 * 
 * @ClassName: JsonBuilder
 * @Description: TODO (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午5:14:29
 */
public class JsonBuilder
{
    
    private JsonBuilder(){
        
    }
    /**
     * 得到JsonBuilder实例化对象
     * 
     * @return
     */
    public static JsonBuilder getInstance()
    {
        return JsonHolder.JSON_BUILDER;
    }

    /**
     * 静态内部类
     * 
     * @author zjj
     */
    private static class JsonHolder
    {
        private static final JsonBuilder JSON_BUILDER = new JsonBuilder();
    }

    /**
     * 将一个数据实体解析成Json数据格式
     * 
     * @param obj
     * @return
     */
    public String toJson(Object obj)
    {
        try
        {
            return JSON.toJSONString(obj, new SerializerFeature[]
            { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty });
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
    public String toJsonExclude(Object obj)
    {
        try
        {
            return JSON.toJSONString(obj);
        }
        catch (Exception e)
        {
            return "";
        }
    }


    /**
     * 为分页列表提供Json封装
     * 
     * @param count
     *            记录总数
     * @param entities
     *            实体列表
     * @param excludes
     *            在json生成时需要排除的属性名称
     * @return
     */
    public String buildObjListToJson(Long count, List<? extends Object> records, boolean listJson) throws MyException
    {
        try
        {
            StringBuffer pageJson = null;
            // 判断是否展示list的数据
            if (listJson)
            {
                pageJson = new StringBuffer("{totalCount:" + count + "," + "rows" + ":");
            }
            else
            {
                pageJson = new StringBuffer("");
            }
            // 构建集合的json数据
            // StringWriter w = new StringWriter();
            // JsonHolder.mapper.writeValue(w, records);
            // pageJson.append(w);
            // w.close();
            pageJson.append(JSON.toJSONString(records, SerializerFeature.WriteMapNullValue));

            if (listJson)
            {
                pageJson.append("}");
            }
            else
            {
                pageJson.append("");
            }
            return pageJson.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


}
