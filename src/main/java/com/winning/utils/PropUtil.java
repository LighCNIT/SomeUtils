package com.winning.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: PropUtil
 * @Description: TODO (配置文件工具类)
 * @author  Ligh
 * @date 2017年7月5日下午5:23:02
 */

@Component
public class PropUtil
{

    private static PropUtil propUtil = null;

    public static PropUtil getInstance()
    {
        if (propUtil == null)
        {
            propUtil = new PropUtil();
        }
        return propUtil;
    }

    private PropUtil()
    {
    }

    /**
     * 根据key获取配置的值
     * 
     * @param key
     * @return
     */
    public String get(String key)
    {
        try
        {
            Properties prop = PropertiesLoaderUtils.loadAllProperties("config.properties");
            return prop.getProperty(key, "").trim();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据key获取配置的值
     * 
     * @param key
     * @return
     */
    public void set(String key, String value)
    {
        try
        {
            Properties prop = PropertiesLoaderUtils.loadAllProperties("config.properties");
            prop.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream(this.getClass().getClassLoader()
                    .getResource("config.properties").getPath());
            // 将Properties集合保存到流中
            prop.store(fos, "Copyright (c) WinningSoft");
            fos.close();// 关闭流
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getDefault(String key)
    {
        try
        {
            Properties prop = PropertiesLoaderUtils.loadAllProperties("config.properties");
            return prop.getProperty(key, "0").trim();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "0";
    }
    
    
    /**
     * 根据key获取配置的值
     * 
     * @param key
     * @return
     */
    public String getExtra(String key)
    {
        try
        {
            Properties prop = PropertiesLoaderUtils.loadAllProperties("extraconfig.properties");
            return prop.getProperty(key, "").trim();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
