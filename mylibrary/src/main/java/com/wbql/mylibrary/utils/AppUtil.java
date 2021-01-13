package com.wbql.mylibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by liuqiang 2020-11-25 .
 */
public class AppUtil {

   private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }


    public static void KillUnusedActivity() {
        List<WeakReference<Activity>> aList = AppRunData.ACTIVITY_LIST;
        for (int i = aList.size() - 1; i >= 0; i--) {
            Activity ac = aList.get(i).get();
            if(null != ac){
                ac.finish();
                aList.remove(i);
            }
        }
    }

    public static boolean isBackground(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("后台", appProcess.processName);
                    return true;
                } else {
                    Log.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static void HiddenSoftInputMode(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void toggleSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showKeywordMethod(View v, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void clearCurrentFocus(Activity activity) {
        View focusView = activity.getCurrentFocus();
        if (focusView != null) {
            focusView.clearFocus();
        }
    }

    public static void hideKeywordMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusView = activity.getCurrentFocus();
        if (focusView != null) {
            imm.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
