package com.winning.utils;

import java.util.Date;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

import ctd.schema.exception.ValidateException;

/**
 * 
 * @ClassName: ChinaIDNumberUtil
 * @Description: TODO (身份证号码相关)
 * @author  Ligh
 * @date 2017年7月5日下午5:04:35
 */
public class ChinaIDNumberUtil
{
    private static final Pattern pattern = Pattern.compile("[0-9]{17}");
    private static final char[] validateCodes =
    { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
    private static final int[] wi =
    { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    public static String convert15To18(String idNumber) throws ValidateException
    {
        int len = idNumber.length();
        if (!(len == 15 || len == 18))
        {
            throw new ValidateException("lenth!= 15 or 18");
        }
        String ai = "";
        ai = idNumber.substring(0, 6) + "19" + idNumber.substring(6, 15);
        if (len == 18)
        {
            ai = idNumber.substring(0, 17);
        }
        else
        {
            ai = idNumber.substring(0, 6) + "19" + idNumber.substring(6, 15);
        }
        if (!pattern.matcher(ai).matches())
        {
            throw new ValidateException("[0-17] must be number");
        }

        String birth = ai.substring(6, 14);
        try
        {
            LocalDate birthDay = DateTimeFormat.forPattern("yyyyMMdd").parseLocalDate(birth);
            LocalDate now = new LocalDate();
            int age = Years.yearsBetween(birthDay, now).getYears();
            if (age < 0 || age > 120)
            {
                throw new ValidateException("BirthdayOverflow[" + birth + "]");
            }
        }
        catch (RuntimeException e)
        {
            throw new ValidateException("BirthdayDateInvaid[" + birth + "]");
        }
        int sum = 0;
        for (int i = 0; i < 17; i++)
        {
            sum += Integer.parseInt(String.valueOf(ai.charAt(i))) * wi[i];
        }
        int mod = sum % 11;
        char c = validateCodes[mod];
        if (idNumber.length() == 18 && idNumber.charAt(17) != c)
        {
            throw new ValidateException("validatecode was wrong!");

        }
        ai += c;

        return ai;
    }

    /**
     * 传入18位身份证,获取出生日期
     *
     * @author Ligh
     * @date 2016-1-21 上午11:19:42
     * @param idNumber
     *            18位身份证号
     * @throws ValidateException
     *             校验异常，身份证必须18位
     * @return yyyyMMdd
     */
    public static Date getBirthFromIDNumber(String idNumber) throws ValidateException
    {

        isValidIDNumber(idNumber);

        String birth = idNumber.substring(6, 14);
        return DateTimeFormat.forPattern("yyyyMMdd").parseLocalDate(birth).toDate();
    }

    public static String getBirthByCertid(String idNumber)
    {
        Date dt = null;
        try
        {
            dt = getBirthFromIDNumber(idNumber);
        }
        catch (ValidateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MyTimeUtil.DateToStr(MyTimeUtil.yyyy_MM_dd, dt);
    }

    /**
     * 传入18位身份证,获取性别
     * @author Ligh
     * @date 2016-1-21 上午11:47:36
     * @param idNumber 18位身份证号
     * @return 1男；2女
     * @throws ValidateException  校验异常，身份证必须18位
     */
    public static String getSexFromIDNumber(String idNumber)
    {
        try
        {
            isValidIDNumber(idNumber);

            int idcardsex = Integer.parseInt(idNumber.substring(idNumber.length() - 2, idNumber.length() - 1));

            return idcardsex % 2 == 0 ? "2" : "1";
        }
        catch (ValidateException e)
        {
            return "0";
        }

    }

    /**
     * 传入18位身份证,获取年龄
     * @author Ligh
     * @date 2016-1-21 上午11:47:36
     * @param idNumber 18位身份证号
     * @throws ValidateException  校验异常，身份证必须18位
     */
    public static Integer getAgeFromIDNumber(String idNumber) throws ValidateException
    {
        isValidIDNumber(idNumber);

        String birth = idNumber.substring(6, 14);
        Integer age;
        try
        {
            LocalDate birthDay = DateTimeFormat.forPattern("yyyyMMdd").parseLocalDate(birth);
            LocalDate now = new LocalDate();
            age = Years.yearsBetween(birthDay, now).getYears();
            if (age < 0 || age > 120)
            {
                throw new ValidateException("BirthdayOverflow[" + birth + "]");
            }
        }
        catch (RuntimeException e)
        {
            throw new ValidateException("BirthdayDateInvaid[" + birth + "]");
        }

        return age;
    }

    private static void isValidIDNumber(String idNumber) throws ValidateException
    {
        int len = idNumber.length();
        if (len != 18)
        {
            throw new ValidateException("lenth!=18");
        }
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println(convert15To18("522725198811120029"));
            // System.out.println(getBirthFromIDNumber("33108119920702674X"));
            // System.out.println(getSexFromIDNumber("33108119920702674X"));
            // System.out.println(getAgeFromIDNumber("33108119920702674X"));
        }
        catch (ValidateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
