package com.winning.utils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @ClassName: MyTimeUtil
 * @Description: TODO (日期字符串转换)
 * @author  Ligh
 * @date 2017年7月5日下午5:17:01
 */
public class MyTimeUtil
{

    public static String yyyy = "yyyy";
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyyMMdd = "yyyyMMdd";
    public static String yyyyMM = "yyyyMM";
    public static String yyyy_MM = "yyyy-MM";
    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String yyyyMMddHH_mm_ss = "yyyyMMddHH:mm:ss";
    public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String MM_dd_yyyy = "MM-dd-yyyy";
    public static String yyyy__MM__dd = "yyyy/MM/dd";
    /**
     * 年月日
     */
    public static String yyyyMMdd_HHmm = "yyyy年MM月dd日 HH时mm分";
    public static String yyyyMMdd_HH_mm_ss = "yyyyMMddHH:mm:ss";
    public static String yyyy_MMdd = "yyyy年MM月dd日";

    public static String getCurrentTime()
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        String str1 = simpleDateFormat.format(now);
        return str1;
    }

    public static String getCurrentTime2()
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd);
        String str1 = simpleDateFormat.format(now);
        return str1;
    }

    /**
     * 将字符串时间改成Date类型
     * 
     * @param format
     * @param dateStr
     * @return
     */
    public static Date strToDate(String format, String dateStr)
    {

        Date date = null;

        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 将Date时间转成字符串
     * 
     * @param format
     * @param date
     * @return
     */
    public static String DateToStr(String format, Date date)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(date);
    }

    /**
     * 获取2个字符日期的天数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 天数差
     */
    public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate)
    {

        Date l_startDate = MyTimeUtil.strToDate(MyTimeUtil.yyyy_MM_dd, p_startDate);
        Date l_endDate = MyTimeUtil.strToDate(MyTimeUtil.yyyy_MM_dd, p_endDate);
        long l_startTime = l_startDate.getTime();
        long l_endTime = l_endDate.getTime();
        long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
        return betweenDays;
    }

    /**
     * 获取2个Date型日期的分钟数差值
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 分钟数差值
     */
    public static long getMinutesOfTowDiffDate(Date p_startDate, Date p_endDate)
    {

        long l_startTime = p_startDate.getTime();
        long l_endTime = p_endDate.getTime();
        long betweenMinutes = (long) ((l_endTime - l_startTime) / (1000 * 60));
        return betweenMinutes;
    }

    /**
     * 获取2个字符日期的天数差
     * 
     * @param p_startDate
     * @param p_endDate
     * @return 天数差
     */
    public static long getDaysOfTowDiffDate(Date l_startDate, Date l_endDate)
    {

        long l_startTime = l_startDate.getTime();
        long l_endTime = l_endDate.getTime();
        long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
        return betweenDays;
    }

    /**
     * 给出日期添加一段时间后的日期
     * 
     * @param dateStr
     * @param plus
     * @return
     */
    public static String getPlusDays(String format, String dateStr, long plus)
    {

        Date date = MyTimeUtil.strToDate(format, dateStr);

        long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

        return MyTimeUtil.DateToStr(format, new Date(time));
    }

    /**
     * 给出日期添加一段时间后的日期
     * 
     * @param dateStr
     * @param plus
     * @return
     */
    public static String getPlusDays(String format, Date date, long plus)
    {

        long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

        return MyTimeUtil.DateToStr(format, new Date(time));
    }

    /**
     * 获取当前日期是星期几
     * 
     * @param dt
     * @return 当前日期是星期几
     * @throws ParseException
     */
    public static String getWeekOfDate(String dt)
    {
        String[] weekDays =
        { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        try
        {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dt));
        }
        catch (ParseException e)
        {
            return "";
        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 根据出生日期计算年龄
     */

    public static String formatAge(String birth)
    {
        String age = "";
        int now = Integer.parseInt(DateToStr(yyyy, new Date()));
        int day = Integer.parseInt(birth.substring(0, 4));
        age = String.valueOf(now - day);
        return age;
    }

    /**
     * 增加几分钟后的时间
     */

    public static String addDateMinut(String day, int x)// 返回的是字符串型的时间，输入的
    // 是String day, int x
    {
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd_HH_mm_ss);// 24小时制
        // 引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
        // 量day格式一致
        Date date = null;
        try
        {
            date = format.parse(day);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        // System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);// 24小时制
        date = cal.getTime();
        // System.out.println("after:" + format.format(date)); //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    /**
     * 得到本月的第一天
     * 
     * @return
     */
    public static String getMonthFirstDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return MyTimeUtil.DateToStr(yyyy_MM_dd, calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     * 
     * @return
     */
    public static String getMonthLastDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return MyTimeUtil.DateToStr(yyyy_MM_dd, calendar.getTime());
    }

    public static String GetSysDate(String format, String StrDate, int year, int month, int day)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sFmt = new SimpleDateFormat(format);
        cal.setTime(sFmt.parse((StrDate), new ParsePosition(0)));

        if (day != 0)
        {
            cal.add(cal.DATE, day);
        }
        if (month != 0)
        {
            cal.add(cal.MONTH, month);
        }
        if (year != 0)
        {
            cal.add(cal.YEAR, year);
        }
        return sFmt.format(cal.getTime());
    }

    public static Date GetSysDate(Date StrDate, int year, int month, int day)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(StrDate);

        if (day != 0)
        {
            cal.add(cal.DATE, day);
        }
        if (month != 0)
        {
            cal.add(cal.MONTH, month);
        }
        if (year != 0)
        {
            cal.add(cal.YEAR, year);
        }
        return cal.getTime();
    }

    /**
     * 将字符串时间改成Date类型
     * 
     * @param format
     * @param dateStr
     * @return
     */
    public static java.sql.Date strToSqlDate(String dateStr)
    {

        java.sql.Date date = null;

        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd);
            date = new java.sql.Date(simpleDateFormat.parse(dateStr).getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat(yyyyMMdd).parse(specifiedDay);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat(yyyyMMdd).format(c.getTime());
        return dayAfter;
    }

    public static final int daysBetween(Date early, Date late)
    {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    public static int daysOfTwo(Date fDate, Date oDate)
    {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    public static boolean isPostDue()
    {
        boolean flag = false;
        Calendar c = Calendar.getInstance();
        // 现在的时间(单位：毫秒)
        long nowMills = c.getTimeInMillis();
        // 设置需要的时间
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(PropUtil.getInstance().get("ghhourlimit")));
        c.set(Calendar.MINUTE, Integer.parseInt(PropUtil.getInstance().get("ghminlimit")));
        long setMills = c.getTimeInMillis();
        if (setMills - nowMills <= 0)
        {
            flag = true;
        }
        return flag;
    }

    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";

    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

    /**
     * 
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate)
    {
        return parseDate(strDate, null);
    }

    /**
     * parseDate
     * 
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern)
    {
        Date date = null;
        try
        {
            if (pattern == null)
            {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        }
        catch (Exception e)
        {
        }
        return date;
    }

    /**
     * format date
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date)
    {
        return formatDate(date, null);
    }

    /**
     * format date
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern)
    {
        String strDate = null;
        try
        {
            if (pattern == null)
            {
                pattern = YYYYMMDD;
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        }
        catch (Exception e)
        {
        }
        return strDate;
    }

    /**
     * 取得日期：年
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得日期：年
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 取得日期：年
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int da = c.get(Calendar.DAY_OF_MONTH);
        return da;
    }

    /**
     * 取得当天日期是周几
     * 
     * @param date
     * @return
     */
    public static int getWeekDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.DAY_OF_WEEK);
        return week_of_year - 1;
    }

    /**
     * 取得一年的第几周
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }

    /**
     * getWeekBeginAndEndDate
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String getWeekBeginAndEndDate(Date date, String pattern)
    {
        Date monday = getMondayOfWeek(date);
        Date sunday = getSundayOfWeek(date);
        return formatDate(monday, pattern) + " - " + formatDate(sunday, pattern);
    }

    /**
     * 根据日期取得对应周周一日期
     * 
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date)
    {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期
     * 
     * @param date
     * @return
     */
    public static Date getSundayOfWeek(Date date)
    {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sunday.getTime();
    }

    /**
     * 取得月的剩余天数
     * 
     * @param date
     * @return
     */
    public static int getRemainDayOfMonth(Date date)
    {
        int dayOfMonth = getDayOfMonth(date);
        int day = getPassDayOfMonth(date);
        return dayOfMonth - day;
    }

    /**
     * 取得月已经过的天数
     * 
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月天数
     * 
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得季度第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDateOfSeason(Date date)
    {
        return getFirstDateOfMonth(getSeasonDate(date)[0]);
    }

    /**
     * 取得季度最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDateOfSeason(Date date)
    {
        return getLastDateOfMonth(getSeasonDate(date)[2]);
    }

    /**
     * 取得季度天数
     * 
     * @param date
     * @return
     */
    public static int getDayOfSeason(Date date)
    {
        int day = 0;
        Date[] seasonDates = getSeasonDate(date);
        for (Date date2 : seasonDates)
        {
            day += getDayOfMonth(date2);
        }
        return day;
    }

    /**
     * 取得季度剩余天数
     * 
     * @param date
     * @return
     */
    public static int getRemainDayOfSeason(Date date)
    {
        return getDayOfSeason(date) - getPassDayOfSeason(date);
    }

    /**
     * 取得季度已过天数
     * 
     * @param date
     * @return
     */
    public static int getPassDayOfSeason(Date date)
    {
        int day = 0;

        Date[] seasonDates = getSeasonDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);

        if (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER)
        {// 季度第一个月
            day = getPassDayOfMonth(seasonDates[0]);
        }
        else if (month == Calendar.FEBRUARY || month == Calendar.MAY || month == Calendar.AUGUST
                || month == Calendar.NOVEMBER)
        {// 季度第二个月
            day = getDayOfMonth(seasonDates[0]) + getPassDayOfMonth(seasonDates[1]);
        }
        else if (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER
                || month == Calendar.DECEMBER)
        {// 季度第三个月
            day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1]) + getPassDayOfMonth(seasonDates[2]);
        }
        return day;
    }

    /**
     * 取得季度月
     * 
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date)
    {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nSeason = getSeason(date);
        if (nSeason == 1)
        {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        }
        else if (nSeason == 2)
        {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        }
        else if (nSeason == 3)
        {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        }
        else if (nSeason == 4)
        {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     * 
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     * 
     * @param date
     * @return
     */
    public static int getSeason(Date date)
    {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month)
        {
        case Calendar.JANUARY:
        case Calendar.FEBRUARY:
        case Calendar.MARCH:
            season = 1;
            break;
        case Calendar.APRIL:
        case Calendar.MAY:
        case Calendar.JUNE:
            season = 2;
            break;
        case Calendar.JULY:
        case Calendar.AUGUST:
        case Calendar.SEPTEMBER:
            season = 3;
            break;
        case Calendar.OCTOBER:
        case Calendar.NOVEMBER:
        case Calendar.DECEMBER:
            season = 4;
            break;
        default:
            break;
        }
        return season;
    }

    public static String getStartTime(Date date)
    {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, -24);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return DateToStr(yyyy_MM_dd_HH_mm_ss, todayStart.getTime());
    }

    public static String getEndTime(Date date)
    {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return DateToStr(yyyy_MM_dd_HH_mm_ss, todayEnd.getTime());
    }

    public static String getMerchantId()
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(yyyyMMddHHmmssSSS);
        return "WN" + df.format(date) + (long) (Math.random() * 10000000L);
    }

    public static String getKey()
    {
        String str = "卫宁健康";
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(yyyyMMddHHmmssSSS);
        String s = DigestUtils
                .md5Hex(getContentBytes(str + df.format(date) + RandomStringUtils.randomAlphanumeric(10), "utf-8"));
        return s;
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset)
    {
        if (charset == null || "".equals(charset))
        {
            return content.getBytes();
        }
        try
        {
            return content.getBytes(charset);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static boolean checkPay(String hour, String minute)
    {
        boolean flag = false;
        Calendar c = Calendar.getInstance();
        // 现在的时间(单位：毫秒)
        long nowMills = c.getTimeInMillis();
        // 设置需要的时间
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        c.set(Calendar.MINUTE, Integer.parseInt(minute));
        long setMills = c.getTimeInMillis();
        if (setMills - nowMills <= 0)
        {
            flag = true;
        }
        return flag;
    }

    public static String getCurrentTimeSecond()
    {
        Calendar c = Calendar.getInstance();
        // 现在的时间(单位：毫秒)
        long nowMills = c.getTimeInMillis();
        return String.valueOf(nowMills).substring(0, 10);
    }

    /**
     * 增加几分钟后的时间
     */

    public static String addDateMinute(int x)// 返回的是字符串型的时间，输入的
    // 是String day, int x
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, x);// 24小时制
        Date d = cal.getTime();
        // System.out.println("after:" + format.format(date)); //显示更新后的日期
        cal = null;
        return DateToStr(yyyy_MM_dd_HH_mm_ss, d);

    }

    public static String getCurrentNormalDate()
    {
        return DateToStr(yyyy_MM_dd_HH_mm_ss, new Date());
    }

    public static String getCurrentShortDate()
    {
        return DateToStr(yyyy_MM_dd, new Date());
    }

    public static void main(String[] args) throws CloneNotSupportedException
    {
        // String startDate = MyTimeUtil.getStartTime(MyTimeUtil.GetSysDate(new
        // Date(), 0, 0, 0));
        // String endDate = MyTimeUtil.getEndTime(MyTimeUtil.GetSysDate(new
        // Date(), 0, 0, -1));
        // System.out.println(DateToStr(yyyy_MM_dd, new
        // Date())+"-"+UUIDGenerator.getFullUUID());
        // System.out.println(endDate);
        // String startDate = MyTimeUtil.getStartTime(MyTimeUtil.GetSysDate(new
        // Date(), 0, 0, 0));
        // String endDate = MyTimeUtil.getEndTime(MyTimeUtil.GetSysDate(new
        // Date(), 0, 0, -1));
        // System.out.println(startDate);
        // System.out.println(endDate);
        // TPayInfo tPayInfo = new TPayInfo();
        // tPayInfo.setSubject("测试");
        // final TPayInfo tPayInfo_ = (TPayInfo) tPayInfo.clone();
        // tPayInfo_.setAuthCode("2010052912250001");
        // System.out.println(JSON.toJSONString(tPayInfo_));
        // System.out.println(System.currentTimeMillis());
        // System.out.println(new Date().getTime());
//        System.out.println("2017-06-26 00:00:00".length());
//        System.out.println("2017-06-26".length());
//        System.out.println(MyTimeUtil.DateToStr(MyTimeUtil.yyyy__MM__dd,
//                MyTimeUtil.strToDate(MyTimeUtil.yyyy_MM_dd_HH_mm_ss, "2017-06-26 00:00:00")));
        System.out.println(daysOfTwo(new Date(), strToDate(yyyy_MM_dd_HH_mm_ss, "2017-06-26 00:00:00")));
    }
}
