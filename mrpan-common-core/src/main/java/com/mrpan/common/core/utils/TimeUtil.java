package com.mrpan.common.core.utils;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mrpan on 2016/11/3.
 */
public class TimeUtil {

    protected Locale locale;
    private static final String Simple_Date_Format = "yyyy-MM-dd";
    private static final int Simple_Date_Format_Length = Simple_Date_Format
            .length();
    private static final String Simple_DateTime_Format = "yyyy-MM-dd HH:mm:ss";

    public TimeUtil() {
        this(Locale.getDefault());
    }

    public TimeUtil(Locale locale) {
        this.locale = locale;
    }

    public static final Date getDate() {
        Date date = new Date();
        return date;
    }

    public static final String getYear() {
        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getMonth() {
        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("MM");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String get2Day() {
        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("dd");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getDay() {
        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("d");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getNextDay(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        Date date = cal.getTime();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("d");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getNextDate(int amount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, amount);
        Date date = cal.getTime();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getCurrentDate() {

        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getToday() {

        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("MM-dd");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getTodayMonth() {

        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-01");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getCurrentTime() {

        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("HH:mm:ss");
        shijian = formater.format(date);
        return shijian;
    }

    public static final String getCurrentDateTime() {

        Date date = new Date();
        String shijian;
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd HH:mm:ss");
        shijian = formater.format(date);
        return shijian;
    }

    public static Date getSomeDate(int i) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static Date getSomeDate(Date dtCur, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(dtCur);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static Date getSomeDate(String dtCur, int i) {
        Calendar c = Calendar.getInstance();
        Date dt = strToSysDate(dtCur);
        c.setTime(dt);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static Date setDate(Date dtCur, int h, int m, int s) {
        Calendar c = Calendar.getInstance();
        c.setTime(dtCur);
        c.set(Calendar.HOUR, h);
        c.set(Calendar.MINUTE, m);
        c.set(Calendar.SECOND, s);
        return c.getTime();
    }

    /**
     * 格式化时间格式
     *
     * @param inDate
     *            String 需要格式的字符串
     * @return String 返回格式化好的日期字符串
     */
    public static String formatDatetime(String inDate) {

        char day_sep = 0;
        if (inDate.indexOf("-") > 0) {
            day_sep = '-';
        } else if (inDate.indexOf("/") > 0) {
            day_sep = '/';
        } else if (inDate.indexOf(".") > 0) {
            day_sep = '.';
        }

        if (day_sep == 0) {
            return "";
        }

        String inYear = "", inMonth = "", inDay = "";
        String inHour = "", inMinute = "", inSecond = "";

        inYear = inDate.substring(0, inDate.indexOf(day_sep));
        inDate = inDate.substring(inDate.indexOf(day_sep) + 1);

        if (inDate.indexOf(day_sep) > 0) {
            inMonth = inDate.substring(0, inDate.indexOf(day_sep));
            inDate = inDate.substring(inDate.indexOf(day_sep) + 1);

            if (inDate.indexOf(" ") > 0) {
                inDay = inDate.substring(0, inDate.indexOf(" "));
                inDate = inDate.substring(inDate.indexOf(" ") + 1);
            } else {
                inDay = inDate;
                inDate = "";
            }
        }

        if (inDate.indexOf(":") > 0) {
            inHour = inDate.substring(0, inDate.indexOf(":"));
            inDate = inDate.substring(inDate.indexOf(":") + 1);

            if (inDate.indexOf(":") > 0) {
                inMinute = inDate.substring(0, inDate.indexOf(":"));
                inDate = inDate.substring(inDate.indexOf(":") + 1);

                inSecond = inDate;
            }
        }

        String result = inYear.trim() + "." + inMonth.trim() + "."
                + inDay.trim();
        if (inHour.trim().length() > 0) {
            result += " " + inHour.trim() + ":" + inMinute.trim() + ":"
                    + inSecond.trim();
        } else {
            result += " 00:00:00";
        }

        return result;
    }

    public static String formatDatetime(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatDatetime(String date, String format) {
        Date dt = strToSysDate(date);
        if (dt == null)
            return "";
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(dt);
    }

    /**
     * 比较时间大小
     *
     * @param datetime1
     *            String 第一个日期字符串字符串
     * @param datetime2
     *            String 第一个日期字符串字符串
     * @return boolean 若大于则返回true,否则返回false
     */
    public static boolean compareDatetime(String datetime1, String datetime2) {

        long ldatetime1 = 0;
        long ldatetime2 = 0;

        if (datetime1 != "") {
            if (datetime1.length() == 10) { // 若为2005-03-31格式，则转换为2005-03-31
                // 00:00:00格式
                datetime1 = datetime1 + " 00:00:00";
            }
            ldatetime1 = Timestamp.valueOf(datetime1).getTime();
        }
        if (datetime2 != "") {
            if (datetime2.length() == 10) {
                datetime2 = datetime2 + " 00:00:00";
            }
            ldatetime2 = Timestamp.valueOf(datetime2).getTime();
        }
        if (datetime1 == "") {
            ldatetime1 = 0;
        }
        if (datetime2 == "") {
            ldatetime2 = 0;
        }
        if (ldatetime1 >= ldatetime2) {
            return true;
        } else {
            return false;
        }
    }

    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 字符串转换为普通的日期
     */
    public static java.util.Date strToSysDate(String str) {
        if (null != str && str.length() > 0) {
            try {
                if (str.length() <= Simple_Date_Format_Length) { // 只包含日期。
                    return (new SimpleDateFormat(Simple_Date_Format))
                            .parse(str);
                } else { // 包含日期时间
                    return (new SimpleDateFormat(Simple_DateTime_Format))
                            .parse(str);
                }
            } catch (ParseException error) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static java.util.Date strToSysDate(String day, String hour,
                                              String minite) {
        String str = day + " " + hour + ":" + minite + ":0";
        if (null != str && str.length() > 0) {
            try {
                if (str.length() <= Simple_Date_Format_Length) { // 只包含日期。
                    return (new SimpleDateFormat(Simple_Date_Format))
                            .parse(str);
                } else { // 包含日期时间
                    return (new SimpleDateFormat(Simple_DateTime_Format))
                            .parse(str);
                }
            } catch (ParseException error) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (type == Date.class) {
            return convertToDate(type, value);
        } else if (type == String.class) {
            return convertToString(type, value);
        } else if (type == Boolean.class) {
            return convertToBoolean(type, value);
        } else if (type == Object.class) {
            return convertToObject(type, value);
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    protected static Object convertToString(Class type, Object value) {
        String str;
        java.sql.Timestamp dat;
        boolean ifdate;
        if (value instanceof Date) {
            try {
                dat = (java.sql.Timestamp) value;
                if ((dat.getHours() == 0) && (dat.getMinutes() == 0)
                        && (dat.getSeconds() == 0)) {
                    ifdate = true;
                } else {
                    ifdate = false;
                }
                str = (new SimpleDateFormat(Simple_DateTime_Format))
                        .format(dat);
                if ((str.length() <= Simple_Date_Format_Length) || (ifdate)) { // 只包含日期。
                    str = (new SimpleDateFormat(Simple_Date_Format))
                            .format(dat);
                }
                return str;
            } catch (Exception e) {
                throw new ConversionException("Error converting Date to String");
            }
        }

        if (value instanceof Boolean) {
            try {
                // if (!Constants.ifBoolToStr)
                // return value;
                Boolean obj = (Boolean) value;
                if (obj.booleanValue()) {
                    return "是";
                } else {
                    return "否";
                }

            } catch (Exception e) {
                throw new ConversionException("Error converting Date to String");
            }
        }
        return value.toString();
    }

    protected static Object convertToDate(Class type, Object value) {
        if (value instanceof Date) {
            return value;
        }

        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }
                return new java.sql.Date(strToSysDate((String) value).getTime());
            } catch (Exception pe) {
                throw new ConversionException("Error converting String to Date");
            }
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    protected static Object convertToBoolean(Class type, Object value) {

        if (value instanceof String) {
            try {
                // if (!Constants.ifBoolToStr)
                // return value;
                @SuppressWarnings("unused")
                Boolean obj = null;
                if (value.equals("1")) {
                    return Boolean.valueOf("true");
                } else {
                    return Boolean.valueOf("false");
                }
                // return obj.valueOf((String)value);

            } catch (Exception pe) {
                throw new ConversionException("Error converting String to Date");
            }
        }

        return value;
    }

    protected static Object convertToObject(Class type, Object value) {
        return value;
    }

    public static Timestamp getNowTime() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = formatter.parse(formatter
                .format(new java.util.Date()));
        return new Timestamp(date.getTime());
    }

    public static boolean compareDatetime(String smallDate, String middleDate,
                                          String bigDate) {
        boolean bRet = true;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date small = df.parse(smallDate);
            Date middle = df.parse(middleDate);
            Date big = df.parse(bigDate);
            bRet = TimeUtil.compareDatetime(small, middle, big);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bRet;
    }

    public static boolean compareDatetime(Date smallDate, Date bigDate) {
        boolean bRet = true;
        try {
            bRet = bigDate.compareTo(smallDate) >= 0 ? true : false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bRet;
    }

    public static boolean compareDatetime(Date smallDate, Date middleDate,
                                          Date bigDate) {
        boolean bRet = true;
        try {
            if (middleDate.compareTo(smallDate) < 0
                    || middleDate.compareTo(bigDate) > 0) {
                bRet = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bRet;
    }

    public static boolean compareDatetime(String smallDate, Date middleDate,
                                          String bigDate) {
        boolean bRet = true;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date small = df.parse(smallDate);
            Date big = df.parse(bigDate);
            bRet = TimeUtil.compareDatetime(small, middleDate, big);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bRet;
    }

    /**
     * 返回全年的日期区间
     *
     * @param year
     * @return
     * @throws Exception
     */
    public static String getBeginYearOfMonth(int year, int month)
            throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1, 0, 0, 0);
        return df.format(c.getTime());
    }

    public static String getEndYearOfMonth(int year, int month)
            throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1, 0, 0, 0);
        int d = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE, d);
        c.add(Calendar.SECOND, -1);
        return df.format(c.getTime());
    }

    public static List<String> getEverydayDate(String beginDate, String endDate)
            throws Exception {
        List<String> listDate = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date dtBegin = df.parse(beginDate);
        Date dtEnd = df.parse(endDate);
        long offset = (dtEnd.getTime() - dtBegin.getTime())
                / (24 * 60 * 60 * 1000);
        c.setTime(df.parse(beginDate));
        listDate.add(df.format(c.getTime()));
        for (int i = 0; i < offset; i++) {
            c.add(Calendar.DATE, 1);
            listDate.add(df.format(c.getTime()));
        }
        // String[] ar =listDate.toArray(new String[listDate.size()]);
        return listDate;
    }

    /**
     * 获得当前日期的星期范围时间
     *
     * @return
     */
    public static Date[] getWeekRange() {
        Date[] dt = new Date[2];
        Calendar c = Calendar.getInstance(Locale.CHINA);
        int d = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, -d + 1);
        dt[0] = c.getTime();
        c.setTime(new Date());
        c.add(Calendar.DATE, 7 - d);
        dt[1] = c.getTime();

        return dt;
    }

    public static Date[] getMonthRange() {
        Date[] dt = new Date[2];
        try {
            Calendar c = Calendar.getInstance(Locale.CHINA);
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH) + 1;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = df.parse(y + "-" + m + "-1");
            dt[0] = d;
            d = df.parse(y + "-" + (m + 1) + "-1");
            c.setTime(d);
            c.add(Calendar.DATE, -1);
            dt[1] = c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static Date addDays(Date dt, int days) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(dt);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static String addDays(int days) {
        Date dt = addDays(new Date(), days);
        String str = formatDatetime(dt, "yyyy-MM-dd");
        return str;
    }

    public static Date addMonth(Date dt, int month) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(dt);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static Date addHour(int hour) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    public static String addMonth(int month) {
        Date dt = addMonth(new Date(), month);
        String str = formatDatetime(dt, "yyyy-MM-dd");
        return str;
    }

    public static Date getDateTimeByDay(String time) {
        String strRemindTime = TimeUtil
                .formatDatetime(new Date(), "yyyy-MM-dd") + " " + time;
        Date dt = TimeUtil.strToSysDate(strRemindTime);
        return dt;
    }

    public static Date getDateTimeByWeek(int week, String time) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        int nWeek = 0;
        switch (week) {
            case 1:
                nWeek = Calendar.MONDAY;
                break;
            case 2:
                nWeek = Calendar.TUESDAY;
                break;
            case 3:
                nWeek = Calendar.WEDNESDAY;
                break;
            case 4:
                nWeek = Calendar.THURSDAY;
                break;
            case 5:
                nWeek = Calendar.FRIDAY;
                break;
            case 6:
                nWeek = Calendar.SATURDAY;
                break;
            case 7:
                nWeek = Calendar.SUNDAY;
                break;
        }
        c.set(Calendar.DAY_OF_WEEK, nWeek);
        Date dt = c.getTime();
        String strRemindTime = TimeUtil.formatDatetime(dt, "yyyy-MM-dd") + " "
                + time;
        dt = TimeUtil.strToSysDate(strRemindTime);
        return dt;
    }

    public static Date getDateTimeByMonth(int day, String time) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(Calendar.DATE, day);
        Date dt = c.getTime();
        String strRemindTime = TimeUtil.formatDatetime(dt, "yyyy-MM-dd") + " "
                + time;
        dt = TimeUtil.strToSysDate(strRemindTime);
        return dt;
    }

    public static Date getDateTimeByYear(int month, int day, String time) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        int nWeek = 0;
        switch (month) {
            case 1:
                nWeek = Calendar.JANUARY;
                break;
            case 2:
                nWeek = Calendar.FEBRUARY;
                break;
            case 3:
                nWeek = Calendar.MARCH;
                break;
            case 4:
                nWeek = Calendar.APRIL;
                break;
            case 5:
                nWeek = Calendar.MAY;
                break;
            case 6:
                nWeek = Calendar.JUNE;
                break;
            case 7:
                nWeek = Calendar.JULY;
                break;
            case 8:
                nWeek = Calendar.AUGUST;
                break;
            case 9:
                nWeek = Calendar.SEPTEMBER;
                break;
            case 10:
                nWeek = Calendar.OCTOBER;
                break;
            case 11:
                nWeek = Calendar.NOVEMBER;
                break;
            case 12:
                nWeek = Calendar.DECEMBER;
                break;
        }
        c.set(Calendar.MONTH, nWeek);
        c.set(Calendar.DATE, day);
        Date dt = c.getTime();
        String strRemindTime = TimeUtil.formatDatetime(dt, "yyyy-MM-dd") + " "
                + time;
        dt = TimeUtil.strToSysDate(strRemindTime);
        return dt;
    }

    /**
     * 增加天数
     *
     * @param day
     * @return
     */
    public static Date addDayDate(Date curDate, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, day);
        Date dt = cal.getTime();
        return dt;
    }

    /**
     * 获得下个月的当前时间
     *
     * @param curDate
     * @param month
     * @return
     */
    public static Date addNextMonthDate(Date curDate, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.MONTH, month);
        Date dt = cal.getTime();
        return dt;
    }

    /**
     * 获得当前时间n年以后的时间
     *
     * @param curDate
     * @param year
     * @return
     */
    public static Date addNextYearDate(Date curDate, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.YEAR, year);
        Date dt = cal.getTime();
        return dt;
    }

    /**
     * 获得当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        int nYear = 0;
        Calendar cal = Calendar.getInstance();
        nYear = cal.get(Calendar.YEAR);
        return nYear;
    }

    public static int getCurrentHour() {
        int nYear = 0;
        Calendar cal = Calendar.getInstance();
        nYear = cal.get(Calendar.HOUR_OF_DAY);
        return nYear;
    }

    public static String getYearBegin() {
        String strYear = "";
        Calendar cal = Calendar.getInstance();
        strYear = cal.get(Calendar.YEAR) + "-01-01";
        return strYear;
    }

    /**
     * 获得当前年份
     *
     * @return
     */
    public static int getCurrentMonth() {
        int nYear = 0;
        Calendar cal = Calendar.getInstance();
        nYear = cal.get(Calendar.MONTH) + 1;
        return nYear;
    }

    /**
     * 获得当前年月的起始月份
     *
     * @param year
     * @param month
     * @return
     */
    public static String getCurrentMonthBegin(int year, int month) {
        String strMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        strMonth = formater.format(cal.getTime());
        return strMonth;
    }

    public static String getCurrentMonthBegin() {
        int nYear = getCurrentYear();
        int nMonth = getCurrentMonth();
        return getCurrentMonthBegin(nYear, nMonth);
    }

    /**
     * 获得当前年月的结束月份
     *
     * @param year
     * @param month
     * @return
     */
    public static String getCurrentMonthEnd(int year, int month) {
        String strMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        strMonth = formater.format(cal.getTime());
        return strMonth;
    }

    public static String getNextMonth(int year, int month) {
        String strMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.add(Calendar.MONTH, 1);
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        strMonth = formater.format(cal.getTime());
        return strMonth;
    }

    public static String getNextMonth() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        return getNextMonth(y, m);
    }

    public static String getNextMonth(String strDate) {
        Calendar cal = Calendar.getInstance();
        Date dt = strToSysDate(strDate);
        cal.setTime(dt);
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        return getNextMonth(y, m);
    }

    public static String getPrevMonth(int year, int month) {
        String strMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM-dd");
        strMonth = formater.format(cal.getTime());
        return strMonth;
    }

    public static String getPrevMonth() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        return getPrevMonth(y, m);
    }

    public static String getPrevMonth(String strDate) {
        Calendar cal = Calendar.getInstance();
        Date dt = strToSysDate(strDate);
        cal.setTime(dt);
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        return getPrevMonth(y, m);
    }

    public static String getCurrentMonthEnd() {
        int nYear = getCurrentYear();
        int nMonth = getCurrentMonth();
        return getCurrentMonthEnd(nYear, nMonth);
    }

    /**
     * 相差多少天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long[] subtractDay(String date1, String date2) {
        // 按照传入的格式生成一个simpledateformate对象
        long[] retValue = new long[4];
        SimpleDateFormat sd = new SimpleDateFormat(Simple_DateTime_Format);
        long nd = 1000 * 24 * 60 * 60; // 一天的毫秒数
        long nh = 1000 * 60 * 60; // 一小时的毫秒数
        long nm = 1000 * 60; // 一分钟的毫秒数
        long ns = 1000; // 一秒钟的毫秒数
        long diff = 0;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(date1).getTime() - sd.parse(date2).getTime();
            day = diff / nd; // 计算差多少天
            hour = diff % nd / nh; // 计算差多少小时
            min = diff % nd % nh / nm; // 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            // System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟"
            // + sec + "秒。");
            retValue[0] = day;
            retValue[1] = hour;
            retValue[2] = min;
            retValue[3] = sec;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static long subtractDay(Date begin, Date end) {
        long nd = 1000 * 24 * 60 * 60; // 一天的毫秒数
        long diff = 0;
        diff = end.getTime() - begin.getTime();
        return diff / nd;
    }

    public static long subtractMinute(Date begin, Date end) {
        long nd = 1000 * 24 * 60 * 60; // 一天的毫秒数
        long nh = 1000 * 60 * 60; // 一小时的毫秒数
        long nm = 1000 * 60; // 一分钟的毫秒数
        long ns = 1000; // 一秒钟的毫秒数
        long diff = 0;
        diff = end.getTime() - begin.getTime();
        long min = diff % nd % nh / nm; // 计算差多少分钟
        return min;
    }

    public static int CalcAge(String birthDay) {
        Date dt = strToSysDate(birthDay);
        return CalcAge(dt);
    }

    public static int CalcAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    private static String getDateOffset(String beginDate, String endDate) {
        String result = "";
        long[] offsetDay = TimeUtil.subtractDay(endDate, beginDate);
        if (offsetDay[0] > 0) {
            result = offsetDay[0] + "天";
        } else if (offsetDay[0] < 0) {
            result = (offsetDay[0]) + "天";
        } else {
            if (offsetDay[1] > 0) {
                result = offsetDay[1] + "时";
            } else if (offsetDay[1] < 0) {
                result = offsetDay[1] + "时";
            } else {
                if (offsetDay[2] > 0) {
                    result = offsetDay[2] + "分";
                }
                if (offsetDay[2] < 0) {
                    result = offsetDay[2] + "分";
                } else {
                    if (offsetDay[3] > 0) {
                        result = offsetDay[3] + "秒";
                    }
                    if (offsetDay[3] < 0) {
                        result = offsetDay[3] + "秒";
                    } else {
                        result = "";
                    }
                }
            }
        }
        return result;
    }

    /**
     * 增加当前时间
     *
     * @param date
     * @return
     */
    public static Date addNowTime(Date date) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        c.setTime(date);
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    public static String getOracleDateTime(Date date) {
        String str = formatDatetime(date, Simple_DateTime_Format);
        String result = "to_date('" + str + "','yyyy-mm-dd hh24:mi:ss')";
        return result;
    }

    public static String getOracleBeginDateTime(String beginDate) {
        String strBeginDate = "to_date('" + beginDate
                + " 0:0:0','yyyy-mm-dd hh24:mi:ss')";
        return strBeginDate;
    }

    public static String getOracleBeginDateTime(Date beginDate) {
        String strBeginDate = "to_date('"
                + formatDatetime(beginDate, "yyyy-MM-dd")
                + " 0:0:0','yyyy-mm-dd hh24:mi:ss')";
        return strBeginDate;
    }

    public static String getOracleEndDateTime(String endDate) {
        String strBeginDate = "to_date('" + endDate
                + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        return strBeginDate;
    }

    public static String getOracleEndDateTime(Date endDate) {
        String strBeginDate = "to_date('"
                + formatDatetime(endDate, "yyyy-MM-dd")
                + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        return strBeginDate;
    }

    public static String getOracleDate(Date date) {
        String str = formatDatetime(date, Simple_DateTime_Format);
        String result = "to_date('" + str + "','yyyy-mm-dd')";
        return result;
    }

    public static String getOracleDate(String beginDate) {
        String strBeginDate = "to_date('" + beginDate + "','yyyy-mm-dd')";
        return strBeginDate;
    }

    public static boolean isSameDay(Date dt1, Date dt2) {
        int year1 = 0, month1 = 0, day1 = 0;
        int year2 = 0, month2 = 0, day2 = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        year1 = c.get(Calendar.YEAR);
        month1 = c.get(Calendar.MONTH);
        day1 = c.get(Calendar.DATE);

        c.setTime(dt2);
        year2 = c.get(Calendar.YEAR);
        month2 = c.get(Calendar.MONTH);
        day2 = c.get(Calendar.DATE);

        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Date dt1 = new Date(2015, 11, 7, 19, 30, 2);
        Date dt2 = new Date(2015, 11, 7, 19, 40, 1);

        long b = subtractMinute(dt1, dt2);
        System.out.println(b);
    }
}
