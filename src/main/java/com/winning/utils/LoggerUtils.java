package com.winning.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志相关
 * @ClassName: LoggerUtils
 * @Description: TODO (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午5:14:59
 */
public class LoggerUtils
{
    /**
     * 是否开启Debug
     */
    public static boolean isDebug = LogManager.getLogger(LoggerUtils.class).isDebugEnabled();

    /**
     * Debug 输出
     * @param clazz  	目标.Class
     * @param message	输出信息
     */
    public static void debug(Class<? extends Object> clazz, String message)
    {
        if (!isDebug)
            return;
        Logger logger = LogManager.getLogger(clazz);
        logger.debug(message);
    }

    /**
     * Debug 输出
     * @param clazz  	目标.Class
     * @param fmtString 输出信息key
     * @param value		输出信息value
     */
    public static void fmtDebug(Class<? extends Object> clazz, String fmtString, Object... value)
    {
        if (!isDebug)
            return;
        if (StringUtils.isBlank(fmtString))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            fmtString = String.format(fmtString, value);
        }
        debug(clazz, fmtString);
    }

    /**
     * Error 输出
     * @param clazz  	目标.Class
     * @param message	输出信息
     * @param e			异常类
     */
    public static void error(Class<? extends Object> clazz, String message, Exception e)
    {
        Logger logger = LogManager.getLogger(clazz);
        if (null == e)
        {
            logger.error(message);
            return;
        }
        logger.error(message, e);
    }

    /**
     * Error 输出
     * @param clazz  	目标.Class
     * @param message	输出信息
     */
    public static void error(Class<? extends Object> clazz, String message)
    {
        error(clazz, message, null);
    }

    /**
     * 异常填充值输出
     * @param clazz 	目标.Class
     * @param fmtString	输出信息key
     * @param e			异常类
     * @param value		输出信息value
     */
    public static void fmtError(Class<? extends Object> clazz, Exception e, String fmtString, Object... value)
    {
        if (StringUtils.isBlank(fmtString))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString, e);
    }

    /**
     * 异常填充值输出
     * @param clazz		目标.Class
     * @param fmtString 输出信息key
     * @param value		输出信息value
     */
    public static void fmtError(Class<? extends Object> clazz, String fmtString, Object... value)
    {
        if (StringUtils.isBlank(fmtString))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString);
    }
}
