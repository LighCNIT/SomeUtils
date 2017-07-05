/**
 * 
 */
package com.winning.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.util.DefaultPropertiesPersister;

/**
 * 数据库解密
 * @ClassName: MyPropertiesPersist
 * @Description: TODO (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午5:16:26
 */
public class MyPropertiesPersist extends DefaultPropertiesPersister
{

	public void load(Properties props, InputStream is) throws IOException
	{

		Properties properties = new Properties();
		properties.load(is);

		if ((properties.get("jdbcOne.username") != null))
		{
			String username = DesUtils.getInstance().decrypt(properties.getProperty("jdbcOne.username"));
			properties.setProperty("jdbcOne.username", username);
		}
		if ((properties.get("jdbcOne.password") != null))
		{
			String password = DesUtils.getInstance().decrypt(properties.getProperty("jdbcOne.password"));
			properties.setProperty("jdbcOne.password", password);
		}
//		if ((properties.get("jdbcTwo.username") != null))
//		{
//			String username = DesUtils.getInstance().decrypt(properties.getProperty("jdbcTwo.username"));
//			properties.setProperty("jdbcTwo.username", username);
//		}
//		if ((properties.get("jdbcTwo.password") != null))
//		{
//			String password = DesUtils.getInstance().decrypt(properties.getProperty("jdbcTwo.password"));
//			properties.setProperty("jdbcTwo.password", password);
//		}
//		if ((properties.get("jdbcThree.username") != null))
//		{
//			String username = DesUtils.getInstance().decrypt(properties.getProperty("jdbcThree.username"));
//			properties.setProperty("jdbcThree.username", username);
//		}
//		if ((properties.get("jdbcThree.password") != null))
//		{
//			String password = DesUtils.getInstance().decrypt(properties.getProperty("jdbcThree.password"));
//			properties.setProperty("jdbcThree.password", password);
//		}
		OutputStream outputStream = null;
		try
		{
			outputStream = new ByteArrayOutputStream();
			properties.store(outputStream, "");
			is = outStream2InputStream(outputStream);
			super.load(props, is);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			outputStream.close();
		}
	}

	private InputStream outStream2InputStream(OutputStream out)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(bos.toByteArray());
		return swapStream;
	}
}
