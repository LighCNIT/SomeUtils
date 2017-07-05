/**
 * 
 */
package com.winning.utils;

import java.security.Key;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

/**
 * Des加密
 * @ClassName: DesUtils
 * @Description: TODO (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午5:10:27
 */
public class DesUtils
{

	private static String strDefaultKey = "winning";
	private Cipher encryptCipher;
	private Cipher decryptCipher;
	private static DesUtils desUtils = null;
	
	public static DesUtils getInstance(){
		if(desUtils==null){
			desUtils = new DesUtils();
		}
		return desUtils;
	}

	private String byteArr2HexStr(byte arrB[])
	{
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++)
		{
			int intTmp;
			for (intTmp = arrB[i]; intTmp < 0; intTmp += 256);
			if (intTmp < 16)
				sb.append("0");
			sb.append(Integer.toString(intTmp, 16));
		}

		return sb.toString();
	}

	private byte[] hexStr2ByteArr(String strIn)
	{
		byte arrB[] = strIn.getBytes();
		int iLen = arrB.length;
		byte arrOut[] = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2)
		{
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}

		return arrOut;
	}

	private DesUtils()
	{
		this(strDefaultKey);
	}

	private DesUtils(String strKey)
	{
		try
		{
			encryptCipher = null;
			decryptCipher = null;
			Security.addProvider(new SunJCE());
			Key key = getKey("5600b65f841f5d78014c9af12be3d1cb".getBytes());
			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(1, key);
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(2, key);
		}
		catch (Exception e)
		{
		}
		
	}

	private byte[] encrypt(byte arrB[])
	{
		try
		{
			return encryptCipher.doFinal(arrB);
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String encrypt(String strIn)
	{
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	private byte[] decrypt(byte arrB[])
	{
		try
		{
			return decryptCipher.doFinal(arrB);
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String strIn)
	{
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte arrBTmp[])
	{
		byte arrB[] = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
			arrB[i] = arrBTmp[i];

		Key key = new SecretKeySpec(arrB, "DES");
		return key;
	}
	
	public static void main(String[] args)
	{
		try
		{
			System.out.println(DesUtils.getInstance().encrypt("123"));
		}
		catch (Exception e)
		{
		}
		
	}
	
	

}
