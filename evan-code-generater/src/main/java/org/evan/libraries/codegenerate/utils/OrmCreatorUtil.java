package org.evan.libraries.codegenerate.utils;

public class OrmCreatorUtil {
    public static String toFirstCharUpcase(String str) {
        String tmp1 = str.substring(0, 1);
        String tmp2 = str.substring(1, str.length());
        return tmp1.toUpperCase() + tmp2;
    }

    public static String toFirstCharLow(String str) {
        String tmp1 = str.substring(0, 1);
        String tmp2 = str.substring(1, str.length());
        return tmp1.toLowerCase() + tmp2;
    }

    public static String convertDbNameToJavaName(String dbName) {
        StringBuilder str = new StringBuilder();
        String[] tmps = dbName.toLowerCase().split("_");

        for (String tmp : tmps) {
            str.append(toFirstCharUpcase(tmp));
        }
        return str.toString();
    }

    public static String convertJavaDataTypeToSimple(String javaDataType) {
        String simpleDataType = null;

        if ("Integer".equals(javaDataType)) {
            simpleDataType = "int";
        } else if ("Long".equals(javaDataType)) {
            simpleDataType = "long";
        } else {
            simpleDataType = "string";
        }

        return simpleDataType;
    }

    public static String convertTableNameToTableAlias(String tableName) {
        StringBuilder str = new StringBuilder();
        String[] tmps = tableName.toLowerCase().split("_");

        for (String tmp : tmps) {
            str.append(tmp.charAt(0));
        }
        return str.toString().toLowerCase();
    }

    public static String convertTableNameToSubPackageName(String tableName) {
        return tableName.replace("_", "").toLowerCase();
    }
}