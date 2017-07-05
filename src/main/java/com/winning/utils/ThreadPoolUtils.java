package com.winning.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @ClassName: ThreadPoolUtils
 * @Description: TODO (线程辅助类)
 * @author  Ligh
 * @date 2017年7月5日下午5:26:38
 */
public class ThreadPoolUtils
{

	private ThreadPoolUtils()
	{

	}

	private static ExecutorService service;

	static
	{
		if (service == null)
		{
			service = Executors.newFixedThreadPool(2000);
		}
	}

	/**
	 * 从线程池中抽取线程，执行指定的Runnable对象
	 * 
	 * @param runnable
	 */
	public static void execute(Runnable runnable)
	{
		service.execute(runnable);
	}

}