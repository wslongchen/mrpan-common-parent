package com.mrpan.common.core.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mrpan on 2016/11/3.
 */
public class QueryKit {

    public static String createQuerySql(Map<String, Object> map)
            throws Exception {
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

    public static String createQuerySql(List<ThreeObject> list)
            throws Exception {
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
                if ("in".equalsIgnoreCase(obj.getThird().toString()
                        .toLowerCase())) {
                    String second = obj.getSecond().toString().trim();
                    if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                        second = "(" + second + ")";
                    }
                    sqlString += obj.getFirst().toString() + " "
                            + obj.getThird().toString() + " " + second;
                } else {
                    sqlString += obj.getFirst().toString()
                            + obj.getThird().toString() + obj.getSecond();
                }

            } else if (obj.getSecond() instanceof String) {
                if ("in".equalsIgnoreCase(obj.getThird().toString()
                        .toLowerCase())) {
                    String second = obj.getSecond().toString().trim();
                    if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                        second = "(" + second + ")";
                    }
                    sqlString += obj.getFirst().toString() + " "
                            + obj.getThird().toString() + " " + second;
                } else if (">=".equalsIgnoreCase(obj.getThird().toString())
                        || "<=".equalsIgnoreCase(obj.getThird().toString())) {
                    sqlString += obj.getFirst().toString() + " "
                            + obj.getThird().toString() + " " + obj.getSecond();
                } else if ("".equalsIgnoreCase(obj.getThird().toString())) {
                    sqlString += obj.getFirst().toString()
                            + obj.getThird().toString()
                            + obj.getSecond().toString();
                } else {
                    sqlString += obj.getFirst().toString() + " "
                            + obj.getThird().toString() + " '"
                            + obj.getSecond() + "'";
                }
            } else if (obj.getSecond() instanceof Date) {
                sqlString += obj.getFirst().toString()
                        + obj.getThird().toString()
                        + convertSqlDate((Date) obj.getSecond());
            }
            sb.append(sqlString);
        }
        return sb.toString();
    }

    public static String createQuerySqlByFour(List<FourObject> list)
            throws Exception {
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
                    if ("in".equalsIgnoreCase(obj.getThird().toString()
                            .toLowerCase())) {
                        String second = obj.getSecond().toString().trim();
                        if (!(second.charAt(0) == '(' && second.endsWith(")"))) {
                            second = "(" + second + ")";
                        }
                        sqlString1 += obj.getFirst().toString() + " "
                                + obj.getThird().toString() + " " + second;
                    } else if ("or".equalsIgnoreCase(obj.getThird().toString()
                            .toLowerCase())) {

                    }else {
                        sqlString1 += obj.getFirst().toString()
                                + obj.getThird().toString() + obj.getSecond();
                    }

                } else if (obj.getSecond() instanceof String) {
                    if ("in".equalsIgnoreCase(obj.getThird().toString()
                            .toLowerCase())) {
                        String second = obj.getSecond().toString().trim();
                        if (StringUtils.isNotBlank(second) && !(second.charAt(0) == '(' && second.endsWith(")"))) {
                            second = "(" + second + ")";
                        }
                        sqlString1 += obj.getFirst().toString() + " "
                                + obj.getThird().toString() + " " + second;
                    } else if (">=".equalsIgnoreCase(obj.getThird().toString())
                            || "<=".equalsIgnoreCase(obj.getThird().toString())) {
                        sqlString1 += obj.getFirst().toString() + " "
                                + obj.getThird().toString() + " "
                                + obj.getSecond();
                    } else if ("".equalsIgnoreCase(obj.getThird().toString())) {
                        sqlString1 += obj.getFirst().toString()
                                + obj.getThird().toString()
                                + obj.getSecond().toString();
                    } else if ("like".equalsIgnoreCase(obj.getThird().toString())) {
                        String second = obj.getSecond().toString();
                        sqlString1 += obj.getFirst().toString() + " " + obj.getThird().toString() + " '%" + second + "%'";
                    }  else {
                        sqlString1 += obj.getFirst().toString() + " "
                                + obj.getThird().toString() + " '"
                                + obj.getSecond() + "'";
                    }
                } else if (obj.getSecond() instanceof Date) {
                    sqlString1 += obj.getFirst().toString()
                            + obj.getThird().toString()
                            + convertSqlDate((Date) obj.getSecond());
                } else if (obj.getFirst() instanceof String[]
                        && obj.getSecond() instanceof int[]) {
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
            } else if ("or".equalsIgnoreCase(obj.getFour().toString()
                    .toLowerCase())) {
                sqlString2 = "";
                if (sb2.length() > 0) {
                    sqlString2 += " " + obj.getFour().toString() + " ";
                }
                if (obj.getSecond() instanceof Integer) {
                    sqlString2 += obj.getFirst().toString()
                            + obj.getThird().toString() + obj.getSecond();
                } else if (obj.getSecond() instanceof String) {
                    sqlString2 += obj.getFirst().toString() + " "
                            + obj.getThird().toString() + " '"
                            + obj.getSecond() + "'";
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

    public static String convertSqlDate(Date dtDate) {
        String result = "to_date('"
                + TimeUtil.formatDatetime(dtDate, "yyyy-MM-dd HH:mm:ss")
                + "' , 'yyyy-mm-dd hh24:mi:ss')";
        return result;
    }

    public static String wrapListToSqlInStr(List<Integer> lst) {
        if (lst == null) {
            return "()";
        }
        StringBuffer temp = new StringBuffer("(");
        for (Integer id : lst) {
            int value = id.intValue();
            if (!"(".equals(temp.toString())) {
                temp.append(",")
                        .append(value);
            } else {
                temp.append(value);
            }
        }
        temp.append(")");
        return temp.toString();
    }

    public static String wrapListToSqlInStr(String[] ids) {
        if (ids.length == 0) {
            return "()";
        }
        StringBuffer temp = new StringBuffer("(");
        for (String id : ids) {
            String value = id;
            if (!"(".equals(temp.toString())) {
                temp.append(",")
                        .append("'" + value + "'");
            } else {
                temp.append("'" + value + "'");
            }
        }
        temp.append(")");
        return temp.toString();
    }

    public static String wrapListToSqlInStr(int[] ids) {
        if (ids.length == 0) {
            return "()";
        }
        StringBuffer temp = new StringBuffer("(");
        for (int id : ids) {
            if (!"(".equals(temp.toString())) {
                temp.append(",")
                        .append(id);
            } else {
                temp.append(id);
            }
        }
        temp.append(")");
        return temp.toString();
    }

    public static String getSqlIn(List<Integer> custIds, int splitNum,
                                  String columnName) {
        if (custIds.size() > 1000) {
            StringBuffer sql = new StringBuffer();
            sql.append(" ").append(columnName).append(" in (");
            for (int i = 0; i < custIds.size(); i++) {
                sql.append(" ").append(custIds.get(i) + " ,");
                if ((i + 1) % splitNum == 0 && (i + 1) < custIds.size()) {
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(") or ").append(columnName).append(" in (");
                }
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            return "(" + sql.toString() + ")";
        } else {
            return columnName + " in " + wrapListToSqlInStr(custIds) + "";
        }
    }

    public static String getSqlIn(String custIds, int splitNum,
                                  String columnName) {
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

    public static void main(String[] args) throws Exception {
        List<FourObject> mapWhere = new ArrayList<FourObject>();
        FourObject obj = new FourObject("name", "123");
        FourObject obj1 = new FourObject("password", 123);
        FourObject obj2 = new FourObject("id", "1,2,3,4", "in");
        FourObject obj3 = new FourObject("account", "asdb", "like");
        FourObject obj4 = new FourObject("email", 1, "<>");
        FourObject obj5 = new FourObject("email", "asdb", "like", "or");
        mapWhere.add(obj);
        mapWhere.add(obj1);
        mapWhere.add(obj2);
        mapWhere.add(obj3);
        mapWhere.add(obj4);
        mapWhere.add(obj5);

        System.out.println(createQuerySqlByFour(mapWhere));

        List<Integer> custIds = new ArrayList<Integer>();
        custIds.add(new Integer(1));
        custIds.add(new Integer(2));
        custIds.add(new Integer(3));
        System.out.println(wrapListToSqlInStr(custIds));

    }

    public static String wrapListToSqlInStr(Integer[] policyIds) {
        if (policyIds.length == 0) {
            return "()";
        }
        StringBuffer temp = new StringBuffer("(");
        for (int id : policyIds) {
            if (!"(".equals(temp.toString())) {
                temp.append(",")
                        .append(id);
            } else {
                temp.append(id);
            }
        }
        temp.append(")");
        return temp.toString();
    }


}

