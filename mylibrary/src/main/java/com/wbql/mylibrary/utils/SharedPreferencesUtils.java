package com.wbql.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {


    public static final String PREFERENCE_NAME = "GoApp_info";

    private static SharedPreferences mSharedPreferences;

    private static SharedPreferencesUtils mPreferencemManager;

    private static SharedPreferences.Editor editor;

    private SharedPreferencesUtils(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }
    public SharedPreferences getSharedPreferences(){
        if (mSharedPreferences == null) {
            mSharedPreferences = AppUtil.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    /**
     * 单例模式，获取instance实例
     */
    public synchronized static SharedPreferencesUtils getInstance() {
        if (mPreferencemManager == null) {
            mPreferencemManager = new SharedPreferencesUtils(AppUtil.getContext());
        }
        return mPreferencemManager;
    }

    public  void setString(String key,String value){
        editor.putString(key, value).apply();
    }
    public  String  getString(String key){
        return mSharedPreferences.getString(key, "");
    }

}
