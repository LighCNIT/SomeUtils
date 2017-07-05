package com.winning.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.winning.exception.MyException;

public class StringUtil {

	public static List<Map<String, Object>> DEPTLIST = new ArrayList<Map<String, Object>>();
	public static List<Map<String, Object>> DOCLIST = new ArrayList<Map<String, Object>>();
	public static List<Map<String, Object>> HOSLIST = new ArrayList<Map<String, Object>>();
	// public static List<Map<String, Object>> DEPTLIST =
	// getCacheList(Constant.DEPTLIST);
	// public static List<Map<String, Object>> DOCLIST =
	// getCacheList(Constant.DOCLIST);

	/**
	 * 判断实体不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isNotNull(Object obj) {
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断实体不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isNull(Object obj) {
		if (null == obj) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 比较两个String
	 * 
	 * @param actual
	 * @param expected
	 * @return
	 *         <ul>
	 *         <li>若两个字符串都为null，则返回true</li>
	 *         <li>若两个字符串都不为null，且相等，则返回true</li>
	 *         <li>否则返回false</li>
	 *         </ul>
	 */
	public static boolean isEquals(String actual, String expected) {
		return ObjectUtils.equals(actual, expected);
	}

	/**
	 * null字符串转换为长度为0的字符串
	 * 
	 * @param str
	 *            待转换字符串
	 * @return
	 * @see
	 * 
	 *      <pre>
	 *      nullStrToEmpty(null) = &quot;&quot;;
	 *      nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
	 *      nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
	 *      </pre>
	 */
	public static String nullStrToEmpty(String str) {
		return (str == null ? "" : str);
	}

	/**
	 * 将字符串首字母大写后返回
	 * 
	 * @param str
	 *            原字符串
	 * @return 首字母大写后的字符串
	 * 
	 *         <pre>
	 *      capitalizeFirstLetter(null)     =   null;
	 *      capitalizeFirstLetter("")       =   "";
	 *      capitalizeFirstLetter("2ab")    =   "2ab"
	 *      capitalizeFirstLetter("a")      =   "A"
	 *      capitalizeFirstLetter("ab")     =   "Ab"
	 *      capitalizeFirstLetter("Abc")    =   "Abc"
	 *         </pre>
	 */
	public static String capitalizeFirstLetter(String str) {
		if (isEmpty(str)) {
			return str;
		}

		char c = str.charAt(0);
		return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
				: new StringBuilder(str.length()).append(Character.toUpperCase(c)).append(str.substring(1)).toString();
	}

	/**
	 * 判断字符串是否是整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isInteger(String number) {
		boolean isNumber = false;
		if (StringUtil.isNotEmpty(number)) {
			isNumber = number.matches("^([1-9]\\d*)|(0)$");
		}
		return isNumber;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		boolean isNotEmpty = false;
		if (str != null && !str.trim().equals("") && !str.trim().equalsIgnoreCase("NULL")) {
			isNotEmpty = true;
		}
		return isNotEmpty;
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 将数组转成SQL认识的字符串 123,432,2312 id in('123','432','2312')
	 * 
	 * @param ids
	 * @return
	 */
	public static String fromArrayToStr(String[] ids) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			str.append("'" + ids[i] + "',");
		}
		if (ids.length > 0) {
			str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}

	/**
	 * 生成6位不重复随机数
	 * 
	 * @return
	 */
	public static int findCheckNumber() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++)
			result = result * 10 + array[i];
		return result;
	}

	/**
	 * 生成6位不重复随机数
	 * 
	 * @return
	 */
	public static String findCheckNum() {
		// 取随机产生的认证码(6位数字)
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			result += rand;
		}
		return result;
	}

	/**
	 * 获取从start开始用*替换len个长度后的字符串
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param start
	 *            开始位置
	 * @param len
	 *            长度
	 * @return 替换后的字符串
	 */
	public static String getMaskStr(String str, int start, int len) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}
		if (str.length() < start) {
			return str;
		}

		// 获取*之前的字符串
		String ret = str.substring(0, start);

		// 获取最多能打的*个数
		int strLen = str.length();
		if (strLen < start + len) {
			len = strLen - start;
		}

		// 替换成*
		for (int i = 0; i < len; i++) {
			ret += "*";
		}

		// 加上*之后的字符串
		if (strLen > start + len) {
			ret += str.substring(start + len);
		}

		return ret;
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	// 使用正则表达式过滤：

	static String reg = PropUtil.getInstance().get("inj_str");

	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	/***************************************************************************
	 * 参数校验
	 * 
	 * @param str
	 */
	public static boolean isValid(String str) {
		if (sqlPattern.matcher(str).find()) {
			return false;
		}
		return true;
	}

	public static String catchExceptionContent(Exception e) {
		StringWriter sw = new StringWriter();
		try {
			e.printStackTrace(new PrintWriter(sw, true));
			return sw.toString();
		} catch (Exception io) {
			// TODO: handle exception
			return "IO异常";
		} finally {
			try {
				if (null != sw) {
					sw.close();
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static String getJsonString(HttpServletRequest request) throws MyException {
		try {
			String json = IOUtils.toString(request.getInputStream());
			return json;
		} catch (Exception e) {
			// TODO: handle exception
			throw new MyException("参数格式异常", Constant.ILLEGAL_AGENT);
		}
	}

	/**
	 * 把数组转换成set
	 * 
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if (null != id) {
				set.add(id);
			}
		}
		return set;
	}

	// 判断号码段所属
	/*
	 * 在使用本方法前，请先验证号码的合法性 规则：
	 * 
	 * 中国移动拥有号码段为:139,138,137,136,135,134,147,159,158,157(3G),151,152,150,182(3G
	 * ),188(3G),187(3G);16个号段
	 * 中国联通拥有号码段为:130,131,132,145,155,156(3G),186(3G),185(3G);8个号段
	 * 中国电信拥有号码段为:133,1349,153,189(3G),180(3G);5个号码段
	 * 
	 * @param mobile要判断的号码
	 * 
	 * @return 返回相应类型：1代表联通；2代表移动；3代表电信
	 */
	public static String getMobileType(String mobile) {

		if (mobile.startsWith("0") || mobile.startsWith("+860")) {
			mobile = mobile.substring(mobile.indexOf("0") + 1, mobile.length());
		}
		List chinaUnicom = Arrays
				.asList(new String[] { "130", "131", "132", "145", "155", "156", "186", "185", "176", "171" });
		List chinaMobile1 = Arrays.asList(new String[] { "135", "136", "137", "138", "139", "147", "150", "151", "152",
				"157", "158", "159", "182", "183", "184", "187", "188", "178" });
		List chinaMobile2 = Arrays
				.asList(new String[] { "1340", "1341", "1342", "1343", "1344", "1345", "1346", "1347", "1348" });
		List chinaTelecom = Arrays.asList(new String[] { "133", "153", "189", "180", "181", "177", "170" });
		boolean bolChinaUnicom = (chinaUnicom.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile1 = (chinaMobile1.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile2 = (chinaMobile2.contains(mobile.substring(0, 4)));
		boolean bolChinaTelecom = (chinaTelecom.contains(mobile.substring(0, 3)));
		if (bolChinaUnicom) {
			System.out.println("联通号码");
			return "1";// 联通
		} else if (bolChinaMobile1 || bolChinaMobile2) {
			System.out.println("移动号码");
			return "2"; // 移动
		} else if (bolChinaTelecom) {
			System.out.println("电信号码");
			return "3"; // 其他为电信
		} else {
			System.out.println("无法识别号码段");
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		// MessageEntity entity = new MessageEntity();
		// entity.setSuccess(true);
		// entity.setMsg("test");
		// System.out.println(JsonBuilder.getInstance().toJson(entity));
		// String str =
		// "{\"err\":null,\"errcode\":\"\",\"msg\":\"test\",\"nonce_str\":\"\",\"sign\":\"\",\"success\":true,\"test\":\"123\"}";
		// MessageEntity messageEntity = JSON.parseObject(str,
		// MessageEntity.class);
		// System.out.println(JsonBuilder.getInstance().toJson(messageEntity));\
		// String str = "123456";
		// String stren = StringUtil.base64Des3Encode(str);
		// System.out.println(Des3.encode(str).getBytes("UTF-8"));
		// System.out.println(Des3.decode("VEkhfK4pUtI="));
		// System.out.println(stren);
		// System.out.println(getMerchantId());
		// System.out.println(GuidGenerator.getFullUUID());
		// System.out.println(AESUtils.decrypt("Oxtb3iVYWfNzvAnSx75tsSRPLF/15NoxB10a3ZR5fmU="));
		// System.out.println(AESUtils.encrypt("18285135809"));
	}

	// public static void vote(final String url, int sum) {
	// // 循环Sum次进行刷票
	// for (int i = 0; i < sum; i++) {
	// try {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// String str;
	// try {
	// str = HttpclientUtil.get(url);
	// System.out.println(str);
	// } catch (NetServiceException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// }).start();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }

	public static String getReturn(Object object) {
		String results = StringUtil.isNotNull(object) ? object.toString() : "";
		return results;
	}

	public static void writeErrLog(Class<? extends Object> clazz, String message) {
		if (StringUtil.isNotEmpty(PropUtil.getInstance().getExtra("errlog"))
				&& StringUtil.isEquals("true", PropUtil.getInstance().getExtra("errlog"))) {
			LoggerUtils.error(clazz, message);
		}

	}

	public static void writeInfoLog(Class<? extends Object> clazz, String message) {
		if (StringUtil.isNotEmpty(PropUtil.getInstance().getExtra("infolog"))
				&& StringUtil.isEquals("true", PropUtil.getInstance().getExtra("infolog"))) {
			LoggerUtils.error(clazz, message);
		}

	}

	public static List<Date> getDelDays(Date date1, int dayNum) {
		List<Date> days = new ArrayList<Date>();
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date1);
			c1.add(Calendar.DAY_OF_MONTH, -dayNum - 1);
			for (int i = 0; i < dayNum; i++) {
				c1.add(Calendar.DAY_OF_MONTH, 1);
				days.add(c1.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	public static String base64Des3Encode(String password) {
		try {
			password = Base64.encode(Des3.encode(password).getBytes("UTF-8"));
			return password;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static String base64Des3Decode(String password) {
		try {
			password = Des3.decode(new String(Base64.decode(password), "UTF-8"));
			return password;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static List<JSONObject> sortListByField(List<JSONObject> list, String name) {
		JsonComparator pComparator = new JsonComparator(name);
		Collections.sort(list, pComparator);
		return list;
	}

	public static List<JSONObject> sortListByFieldDesc(List<JSONObject> list, String name) {
		JsonComparatorDesc pComparator = new JsonComparatorDesc(name);
		Collections.sort(list, pComparator);
		return list;
	}

	/* 去除省市区编码后面的0 */
	public static String delZero(String tempString) {

		int initlen = tempString.length();
		int finallen = initlen;
		int st = 0;
		int off = 0;
		char[] val = new char[initlen];
		tempString.getChars(0, finallen, val, 0);

		// while ((st < finallen) && (val[off + st] <= '0')) {
		// st++;
		// }去掉头空格
		while ((st < finallen) && (val[off + finallen - 1] <= '0')) {
			finallen--;
		}
		return ((st > 0) || (finallen < initlen)) ? tempString.substring(st, finallen) : tempString;
	}
}
