package com.pgyer.shophelper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

import java.io.ObjectStreamException;

/**
 * Created by liuqiang 2021-01-08 .
 */
public class MobileManufacturer {

    private static Context mContext;
    private static final String TAG = "ShearPlateUtil";
    private static MobileManufacturer instance;

    private MobileManufacturer() {

    }


    public static MobileManufacturer getInstance() {//方法无需同步，各线程同时访问
        if (instance == null) {
            synchronized (MobileManufacturer.class) {//在创建对象时再进行同步锁定
                if (instance == null) {
                    instance = new MobileManufacturer();
                }
            }
        }
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }



    public void startMoblieSetting(Activity activity) {
        if (Build.BRAND != null) {
        switch (Build.BRAND.toLowerCase()){
            case "huawei":
            case "honor":
                goHuaweiSetting(activity);
                break;

            case "xiaomi":
            case "redmi":

                goXiaomiSetting(activity);
                break;

            case "oppo":
                goOPPOSetting(activity);
                break;
            case "vivo":
                goVIVOSetting(activity);
                break;
            case "meizu":
                goMeizuSetting(activity);
                break;
            case "samsung":
                goSamsungSetting(activity);
                break;

            case "smartisan":
                goSmartisanSetting(activity);
                break;

            case "letv":
                goLetvSetting(activity);
                break;


        }
        }


    }


    private void goLetvSetting(Activity activity) {
        showActivity("com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity",activity);
    }

    private void goSmartisanSetting(Activity activity) {
        showActivity("com.smartisanos.security",activity);
    }

    private void goHuaweiSetting(Activity activity) {
        try {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity",activity);
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity",activity);
        }
    }


    private void goXiaomiSetting(Activity activity) {
        showActivity("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity",activity);
    }

    private void goOPPOSetting(Activity activity) {
        try {
            showActivity("com.coloros.phonemanager",activity);
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe",activity);
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf",activity);
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter",activity);
                }
            }
        }
    }

    private void goVIVOSetting(Activity activity) {
        showActivity("com.iqoo.secure",activity);
    }


    private void goMeizuSetting(Activity activity) {
        showActivity("com.meizu.safe",activity);
    }

    private void goSamsungSetting(Activity activity) {
        try {
            showActivity("com.samsung.android.sm_cn",activity);
        } catch (Exception e) {
            showActivity("com.samsung.android.sm",activity);
        }
    }



    /**
     * 跳转到指定应用的首页
     */
    private void showActivity(@NonNull String packageName,Activity activity) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        activity.startActivityForResult(intent,1001);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    private void showActivity(@NonNull String packageName, @NonNull String activityDir,Activity activity) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent,1001);
    }

}
