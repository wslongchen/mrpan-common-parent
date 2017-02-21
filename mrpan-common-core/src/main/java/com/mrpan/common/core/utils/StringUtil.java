package com.mrpan.common.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.dubbo.common.utils.StringUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by mrpan on 2017/2/21.
 */
public class StringUtil {
    public static List<String> mergerString(String s1, String s2) {
        String s = s1 + "," + s2;
        List<String> list = new ArrayList<String>();
        if (!"".equalsIgnoreCase(s)) {
            String[] array = s.split(",");
            for (String ss : array) {
                if (!"".equalsIgnoreCase(ss) && !list.contains(ss)) {
                    list.add(ss);
                }
            }
        }
        return list;
    }

    /**
     * 字符串转换
     *
     * @param params
     *            如 param1,param2,param3
     * @return 'param1','param2','param3'
     */
    public static String getAfterSplitString(String params) {
        String retparam = "";
        String[] paramarray = params.split(",");
        for (String paramString : paramarray) {
            if (!"".equals(retparam)) {
                retparam += ",";
            }
            retparam += "'" + paramString + "'";
        }
        return retparam;
    }

    /**
     * 四舍五入
     *
     * @param value
     *            输入的值
     * @param bits
     *            小数点位数
     * @return
     */
    public static double round(double value, int bits) {
        BigDecimal bigdec = new BigDecimal(value);
        return bigdec.setScale(bits, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 截取浮点数的位数，例如12.334 那么办成12.33 如果12.335 页是12.33
     *
     * @param value
     * @param bits
     * @return
     */
    public static double truncate(double value, int bits) {
        BigDecimal bigdec = new BigDecimal(value);
        BigDecimal result = bigdec.setScale(bits, RoundingMode.DOWN);
        return result.doubleValue();
    }

    /**
     *
     * @param value
     *            输入的浮点值
     * @param bits
     *            小数位数
     * @return
     */
    public static String getDoubleString(double value, int bits) {
        String strNum = "0.";
        for (int i = 0; i < bits; i++) {
            strNum += 0;
        }
        BigDecimal bigdec = new BigDecimal(value);
        double newvalue = bigdec.setScale(bits, BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat df = new DecimalFormat(strNum);
        return df.format(newvalue);
    }

    /**
     * 判断是否为数字型
     *
     * @param value
     * @return
     */
    public static boolean isNumeric(String value) {
        boolean ret = false;
        Pattern pattern = Pattern.compile("[0-9]*");
        ret = pattern.matcher(value).matches();
        return ret;
    }

    public static boolean isDouble(String value) {
        boolean ret = false;
        if (!"".equalsIgnoreCase(value.trim())) {
            Pattern pattern = Pattern.compile("[.0-9]*");
            ret = pattern.matcher(value).matches();
        }
        return ret;
    }

    public static int Convert(String param, int defval) {
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static double Convert(String param, double defval) {
        double value = defval;
        if (param != null) {
            try {
                value = Double.parseDouble(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    /**
     *
     * @param barcode
     *            条形码
     * @return 如果是散称条码则返回true，反之false
     */
    public static boolean isBarCode(String barcode) {
        boolean ret = false;
        String reg = "^100\\d{10}$";
        if (barcode.matches(reg)) {
            ret = true;
        }
        return ret;
    }

    /**
     * 手机号码检测
     *
     * @param mobiles
     * @return 手机号码正确返回true错误返回false
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isSuitable(String value, String reg) {
        boolean ret = false;
        if (value.matches(reg)) {
            ret = true;
        }
        return ret;
    }

    private static Map<String, String> referencesMap = new HashMap<String, String>();
    static {
        referencesMap.put("'", "''");
    }

    public static String encodeMysql(String source) {
        if (source == null)
            return "";
        StringBuffer sbuffer = new StringBuffer(source.length());
        for (int i = 0; i < source.length(); i++) {
            String c = source.substring(i, i + 1);
            if (referencesMap.get(c) != null) {
                sbuffer.append(referencesMap.get(c));
            } else {
                sbuffer.append(c);
            }
        }
        return sbuffer.toString();
    }

    public static String JsonStringFormat(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 20);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();

    }

    /**
     * 获取类属性名称
     *
     * @param classname
     *            类Class
     * @return 属性名称数组
     */
    public static String[] getFiledName(Class<?> classname) {
        Field[] fields = classname.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String nameString = fields[i].getName();
            fieldNames[i] = nameString;
        }
        int r = -1;
        for (String s : fieldNames) {
            if (s.equalsIgnoreCase("serialVersionUID")) {
                r = 1;
                break;
            }
        }
        if (r > 0) {
            String[] arr = new String[fieldNames.length - 1];
            System.arraycopy(fieldNames, 1, arr, 0, arr.length);
            return arr;
        } else {
            return fieldNames;
        }
    }

    /**
     *
     * @param a
     *            数组a
     * @param b
     *            数组b
     * @return b数组合并到a数组后面的数组
     */
    public static String[] BarrayToAarray(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static String createQuerySql(Map<String, Object> map) throws Exception {
        if (map == null)
            return "";
        String sqlString = "";

        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sqlString = "";
            if (sb.length() > 0) {
                sqlString += " and ";
            }
            sqlString += key + "='" + map.get(key) + "'";
            sb.append(sqlString);
        }
        return sb.toString();
    }

    public static String createQuerySql(List<ThreeObject> list) throws Exception {
        if (list == null)
            return "";
        String sqlString = "";
        StringBuilder sb = new StringBuilder();
        for (ThreeObject obj : list) {
            sqlString = "";
            if (sb.length() > 0) {
                sqlString += " and ";
            }
            if (obj.getSecond() instanceof Integer) {
                if ("in".equalsIgnoreCase(obj.getThird().toString().toLowerCase())) {
                    String second = obj.getSecond().toString().trim();
                    if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                        second = "(" + second + ")";
                    }
                    sqlString += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + second;
                } else {
                    sqlString += obj.getFirst().toString() + obj.getThird().toString() + obj.getSecond();
                }

            } else if (obj.getSecond() instanceof String) {
                if ("in".equalsIgnoreCase(obj.getThird().toString().toLowerCase())) {
                    String second = obj.getSecond().toString().trim();
                    if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                        second = "(" + second + ")";
                    }
                    sqlString += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + second;
                } else if (">=".equalsIgnoreCase(obj.getThird().toString()) || "<=".equalsIgnoreCase(obj.getThird().toString())) {
                    sqlString += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + obj.getSecond();
                } else if ("".equalsIgnoreCase(obj.getThird().toString())) {
                    sqlString += obj.getFirst().toString() + obj.getThird().toString() + obj.getSecond().toString();
                } else {
                    sqlString += obj.getFirst().toString() + " " + obj.getThird().toString() + " '" + obj.getSecond() + "'";
                }
            } else if (obj.getSecond() instanceof Date) {
                sqlString += obj.getFirst().toString() + obj.getThird().toString() + convertSqlDate((Date) obj.getSecond());
            }

            sb.append(sqlString);
        }
        return sb.toString();
    }

    public static String createQuerySqlByFour(List<FourObject> list) throws Exception {
        if (list == null)
            return "";
        String sqlString1 = "", sqlString2 = "";
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (FourObject obj : list) {
            if ("and".equalsIgnoreCase(obj.getFour().toString().toLowerCase())) {
                sqlString1 = "";
                if (sb1.length() > 0) {
                    sqlString1 += " " + obj.getFour().toString() + " ";
                }
                if (obj.getSecond() instanceof Integer) {
                    if ("in".equalsIgnoreCase(obj.getThird().toString().toLowerCase())) {
                        String second = obj.getSecond().toString().trim();
                        if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                            second = "(" + second + ")";
                        }
                        sqlString1 += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + second;
                    } else if ("or".equalsIgnoreCase(obj.getThird().toString().toLowerCase())) {

                    } else {
                        sqlString1 += obj.getFirst().toString() + obj.getThird().toString() + obj.getSecond();
                    }

                } else if (obj.getSecond() instanceof String) {
                    if ("in".equalsIgnoreCase(obj.getThird().toString().toLowerCase())) {
                        String second = obj.getSecond().toString().trim();
                        if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                            second = "(" + second + ")";
                        }
                        sqlString1 += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + second;
                    } else if (">=".equalsIgnoreCase(obj.getThird().toString()) || "<=".equalsIgnoreCase(obj.getThird().toString())) {
                        sqlString1 += obj.getFirst().toString() + " " + obj.getThird().toString() + " " + obj.getSecond();
                    } else if ("".equalsIgnoreCase(obj.getThird().toString())) {
                        sqlString1 += obj.getFirst().toString() + obj.getThird().toString() + obj.getSecond().toString();
                    } else {
                        sqlString1 += obj.getFirst().toString() + " " + obj.getThird().toString() + " '" + obj.getSecond() + "'";
                    }
                } else if (obj.getSecond() instanceof Date) {
                    sqlString1 += obj.getFirst().toString() + obj.getThird().toString() + convertSqlDate((Date) obj.getSecond());
                } else if (obj.getFirst() instanceof String[] && obj.getSecond() instanceof int[]) {
                    String str = "";
                    String[] firstList = (String[]) obj.getFirst();
                    int[] secondList = (int[]) obj.getSecond();
                    for (int i = 0; i < firstList.length; i++) {
                        if (!"".equalsIgnoreCase(str)) {
                            str += " " + obj.getFour() + " ";
                        }
                        str += firstList[i] + obj.getThird() + secondList[i];
                    }
                    sqlString1 += "(" + str + ")";
                }

                sb1.append(sqlString1);
            } else if ("or".equalsIgnoreCase(obj.getFour().toString().toLowerCase())) {
                sqlString2 = "";
                if (sb2.length() > 0) {
                    sqlString2 += " " + obj.getFour().toString() + " ";
                }
                if (obj.getSecond() instanceof Integer) {
                    sqlString2 += obj.getFirst().toString() + obj.getThird().toString() + obj.getSecond();
                } else if (obj.getSecond() instanceof String) {
                    sqlString2 += obj.getFirst().toString() + " " + obj.getThird().toString() + " '" + obj.getSecond() + "'";
                }
                sb2.append(sqlString2);
            }
        }
        if (sb2.length() > 0) {
            return "(" + sb1.toString() + ") or(" + sb2.toString() + ")";
        } else {
            return sb1.toString();
        }

    }

    private static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
            textStr = textStr.replaceAll("&nbsp;", "");

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }

    public static String convertSqlDate(Date dtDate) {
        String result = "to_date('" + TimeUtil.formatDatetime(dtDate, "yyyy-MM-dd HH:mm:ss") + "' , 'yyyy-mm-dd hh24:mi:ss')";
        return result;
    }

    public static String convertSqlDate(String strDate) {
        String result = "to_date('" + strDate + "' , 'yyyy-mm-dd hh24:mi:ss')";
        return result;
    }

    // public static String convertSqlFieldDate(String strDate) {
    // String result = "to_date(" + strDate + " , 'yyyy-mm-dd hh24:mi:ss')";
    // return result;
    // }

    public static String cutString(String strValue) {
        return cutString(strValue, 10);
    }

    public static String cutString(String strValue, int nLen) {
        if (strValue.length() < nLen) {
            return strValue;
        } else {
            return strValue.substring(0, nLen) + "...";
        }
    }

    /**
     * 获得首字母的汉语拼音
     *
     * @param strCN
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getFirstHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
        if (null == strCN) {
            return null;
        }
        StringBuffer firstSpell = new StringBuffer();
        char[] charOfCN = strCN.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charOfCN.length; i++) {
            // 是否为中文字符
            if (charOfCN[i] > 128) {
                String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], format);
                // 取拼音首字母
                if (null != spellArray) {
                    firstSpell.append(spellArray[0].charAt(0));
                } else {
                    firstSpell.append(charOfCN[i]);
                }
            } else {
                firstSpell.append(charOfCN[i]);
            }
        }
        return firstSpell.toString();
    }

    /**
     * 获取汉字串拼音
     *
     * @param strCN
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
        if (null == strCN) {
            return null;
        }
        StringBuffer spell = new StringBuffer();
        char[] charOfCN = strCN.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charOfCN.length; i++) {
            // 是否为中文字符
            if (charOfCN[i] > 128) {
                String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
                if (null != spellArray) {
                    spell.append(spellArray[0]);
                } else {
                    spell.append(charOfCN[i]);
                }
            } else {
                spell.append(charOfCN[i]);
            }
        }
        return spell.toString();
    }

    public static String getFirstPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
        if (null == strCN) {
            return null;
        }
        StringBuffer firstSpell = new StringBuffer();
        StringBuffer spell = new StringBuffer();
        char[] charOfCN = strCN.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charOfCN.length; i++) {
            // 是否为中文字符
            if (charOfCN[i] > 128) {
                String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
                if (null != spellArray) {
                    firstSpell.append(spellArray[0].charAt(0));
                    spell.append(spellArray[0]);
                } else {
                    firstSpell.append(charOfCN[i]);
                    spell.append(charOfCN[i]);
                }
            } else {
                firstSpell.append(charOfCN[i]);
                spell.append(charOfCN[i]);
            }
        }
        return firstSpell.toString();
    }

    /**
     * 获取 汉字串拼音首字母 & 汉字串拼音
     *
     * @param strCN
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String[] getFirstAndHanyuPinyin(String strCN) throws BadHanyuPinyinOutputFormatCombination {
        if (null == strCN) {
            return null;
        }
        StringBuffer firstSpell = new StringBuffer();
        StringBuffer spell = new StringBuffer();
        char[] charOfCN = strCN.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charOfCN.length; i++) {
            // 是否为中文字符
            if (charOfCN[i] > 128) {
                String[] spellArray = PinyinHelper.toHanyuPinyinStringArray(charOfCN[i], defaultFormat);
                if (null != spellArray) {
                    firstSpell.append(spellArray[0].charAt(0));
                    spell.append(spellArray[0]);
                } else {
                    firstSpell.append(charOfCN[i]);
                    spell.append(charOfCN[i]);
                }
            } else {
                firstSpell.append(charOfCN[i]);
                spell.append(charOfCN[i]);
            }
        }
        return new String[] { firstSpell.toString(), spell.toString() };
    }

    public static String formatSqlIn(String strValue) throws Exception {
        String sRet = "";
        try {
            String[] ar = strValue.split(",");
            sRet = "'" + StringUtils.join(ar, "','") + "'";
        } catch (Exception ex) {
            throw ex;
        }
        return sRet;
    }

    public static String getSqlIn(String custIds, int splitNum, String columnName) {
        String[] arCustId = custIds.split(",");
        if (arCustId.length > 1000) {
            StringBuffer sql = new StringBuffer();
            sql.append(" ").append(columnName).append(" in (");
            for (int i = 0; i < arCustId.length; i++) {
                sql.append(" ").append(arCustId[i] + " ,");
                if ((i + 1) % splitNum == 0 && (i + 1) < arCustId.length) {
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(") or ").append(columnName).append(" in (");
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            return "(" + sql.toString() + ")";
        } else {
            return columnName + " in (" + custIds + ")";
        }
    }

    /**
     * 生成随机码
     *
     * @return
     */
    public static String getShuffle(int length) {
        String result = "";
        try {
            String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "1", "0" };
            List list = Arrays.asList(beforeShuffle);
            Collections.shuffle(list);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
            }
            String afterShuffle = sb.toString();
            if (afterShuffle.length() < length) {
                length = afterShuffle.length();
            }
            result = afterShuffle.substring(0, length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static String getInviteCode(long seqId) {
        String code = Long.toHexString(seqId);
        String suffix = "";
        if (code.length() < 8) {
            suffix = getShuffle(8 - code.length());
        }
        return (code + suffix).toUpperCase();
    }

    /**
     * 对字符串进行掩码替换处理 如: 王**
     *
     * @param target
     *            需要掩码的串
     * @param startRemain
     *            开头保留位数
     * @param endRemain
     *            结尾保留位数
     * @param maskChar
     *            掩码字符 例如 *
     * @return
     */
    public static String maskStr(String target, int startRemain, int endRemain, String maskChar) {
        int size = 0;
        if (null != target && !"".equals(target)) {
            size = target.length();
        }
        int toBeMasked = size - (startRemain + endRemain);
        StringBuffer sb = new StringBuffer();
        if ((startRemain + endRemain) < size) {
            sb.append(target.substring(0, startRemain));
            for (int i = 0; i < toBeMasked; i++) {
                sb.append(maskChar);
            }
            sb.append(target.substring(size - endRemain));
            return sb.toString();
        } else {
            return target;
        }
    }

    public static String CodeToChinese(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBk"); // 转成中文
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * <p>
     * 根据传入的参数名 删除并返回FourObject对象
     *
     * @param paramStr
     * @param where
     * @author wangzhongping
     * @return
     */
    public static FourObject removeFourObject(String paramStr, List<FourObject> where) {
        FourObject obj = null;
        for (int i = 0; i < where.size(); i++) {
            if (paramStr.equals(where.get(i).getFirst())) {
                obj = where.get(i);
                where.remove(i);
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        try {
            BigDecimal a = new BigDecimal("450.8953820597459");
            BigDecimal result = a.setScale(2, RoundingMode.DOWN);
            System.out.println("a = " + a);
            System.out.println("result = " + StringUtil.truncate(0.006, 2));

        } catch (Exception ex) {

        }
        System.exit(0);
    }
}

