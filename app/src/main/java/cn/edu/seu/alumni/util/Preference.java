package cn.edu.seu.alumni.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preference {

    public static class Key {

        /**
         * 账号相关
         */
        public static final String IS_ACCESS_TOKEN_VALID = "IS_ACCESS_TOKEN_VALID";
        public static final String USER_ID = "USER_ID";
        public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

        /**
         * 基本信息
         */
        public static final String IS_MASTER = "IS_MASTER";
        public static final String NAME = "NAME";
        public static final String IMAGE_URL = "IMAGE_URL";
        public static final String SCHOOL = "SCHOOL";    //院系
        public static final String MAJOR = "MAJOR";
        public static final String ENROLL_YEAR = "ENROLL_YEAR";
        public static final String STUDENT_NUM = "STUDENT_NUM";
        public static final String LOCATION = "LOCATION";
        public static final String WEIBO_BIND = "WEIBO_BIND";
        public static final String WEIXIN_BIND = "WEIXIN_BIND";

        /**
         * 详细信息
         */
        public static final String GENDER = "GENDER";
        public static final String BIRTHDAY = "BIRTHDAY";
        public static final String CITY = "CITY";     //工作城市
        public static final String PROFESSION = "PROFESSION";
        public static final String COMPANY = "COMPANY";
        public static final String JOB = "JOB";
        public static final String INTRODUCTION = "INTRODUCTION";
        public static final String EDU_HISTORY = "EDU_HISTORY";
        public static final String JOB_HISTORY = "JOB_HISTORY";


//        public static final String FIRST_USE = "FIRST_USE";
//        public static final String LOGIN = "LOGIN";
//        public static final String VERSION_CODE = "VERSION_CODE";
//        public static final String PHOTO_PATH="PHOTO_Path";
    }

    public static boolean containsKey(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(key);
    }

    public static String getString(Context context, String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static long getLong(Context context,  String key, long defalultValue) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defalultValue);
    }

    public static void putString(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    public static void putInt(Context context, String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).commit();
    }

    public static void putFloat(Context context, String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key, value).commit();
    }

    public static void putLong(Context context, String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, value).commit();
    }

    public static void clearAll(Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
