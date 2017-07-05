package com.winning.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @ClassName: AESUtils
 * @Description: AES加密算法 (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午4:18:40
 */
public class AESUtils {
	
	private static final String KEY_ALGORITHM = "AES";   
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	public static byte[] initSecretKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(128);
		SecretKey  secretKey = kg.generateKey();
		
		return secretKey.getEncoded();
	}
	
	 public static Key toKey(byte[] key){
		 return new SecretKeySpec(key, KEY_ALGORITHM);   
	 }
	 
	 public static byte[] encrypt(byte[] data,Key key) throws Exception{   
		 return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);   
	}
	
	 public static byte[] encrypt(byte[] data,byte[] key) throws Exception{   
		return encrypt(data, toKey(key),DEFAULT_CIPHER_ALGORITHM);   
	}
	 
	 public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{   
		 Cipher cipher = Cipher.getInstance(cipherAlgorithm);   
		 cipher.init(Cipher.ENCRYPT_MODE, key);   
		 return cipher.doFinal(data);   
	}
	
	 public static byte[] decrypt(byte[] data,byte[] key) throws Exception{   
		 return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);   
	}  
	
	 public static byte[] decrypt(byte[] data,Key key) throws Exception{   
		return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);   
	}  
	 
	 public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{   
		 Key k = toKey(key);   
		 return decrypt(data, k, cipherAlgorithm);   
	}
	
	 public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{   
		 Cipher cipher = Cipher.getInstance(cipherAlgorithm);   
		 cipher.init(Cipher.DECRYPT_MODE, key);   
		 return cipher.doFinal(data);   
	}
	 
	 private static String  showByteArray(byte[] data){   
		 if(null == data){   
		             return null;   
		 }   
		 StringBuilder sb = new StringBuilder("{");   
		 for(byte b:data){   
			 sb.append(b).append(",");   
		 }   
		 sb.deleteCharAt(sb.length()-1);   
		 sb.append("}");   
		 return sb.toString();   
	}   

	 
	public static void main(String[] args) throws Exception{
		
		System.out.println(decrypt("2VJVEmT2uiP53FtP9lGBbA=="));
		System.out.println(encrypt("522635197608189322"));
		System.out.println(encrypt("18748992323"));
		System.out.println(encrypt("郭洪"));
	}
	public static String encrypt(String plainText) throws Exception{
		byte[] key = "1234567890123457".getBytes("UTF-8");
		byte[] data = plainText.getBytes("UTF-8");
		byte[] binaryData = AESUtils.encrypt(data, key); 	
		String result = Base64.encodeBase64String(binaryData);
		return result;
	}
	public static String decrypt(String encryptText) throws Exception{
		byte[] key = "1234567890123457".getBytes("UTF-8");
		byte[] data = Base64.decodeBase64(encryptText);
		String result = new String(AESUtils.decrypt(data, key),"UTF-8");
		return result;
	}

}
