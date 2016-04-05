package cn.edu.seu.alumni.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import cn.edu.seu.alumni.application.App;

public class Preference {

    private static SharedPreferences preference = null;

    private static SharedPreferences getSharedPreferences(){
        if (preference == null) {
            synchronized (Preference.class) {
                if (preference == null) {
                   preference = PreferenceManager.getDefaultSharedPreferences(App.getContext());
                }
            }
        }
        return preference;
    }

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

    public static boolean containsKey(String key) {
        return getSharedPreferences().contains(key);
    }

    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    public static long getLong(String key, long defalultValue) {
        return getSharedPreferences().getLong(key, defalultValue);
    }

    public static void putString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).commit();
    }

    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public static void putInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    public static void putFloat(String key, float value) {
        getSharedPreferences().edit().putFloat(key, value).commit();
    }

    public static void putLong(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).commit();
    }

    public static void clearAll() {
        Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }
}
