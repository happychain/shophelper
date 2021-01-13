package com.pgyer.shophelper.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

/**
 * Created by liuqiang 2021-01-08 .
 */
public class AppUtils {

    /**
     * 获得手机中正在运行的有访问网络权限的第三方应用的名字
     *
     * @param context
     * @return
     */
    public static void getRunningAPP(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : list) {
            String[] pkgNameList = appProcess.pkgList;//获取该进程中所有的app包名
            for (String pkgName : pkgNameList) {
                Log.e("name", pkgName);
                boolean isHave = (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission("android.permission.INTERNET", pkgName));
                if (isHave) {
                    try {
                        ApplicationInfo info = packageManager.getApplicationInfo(pkgName, 0);
                        if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {//安装的第三方应用，而不是系统应用
                            Log.e("runningName", info.loadLabel(packageManager).toString());
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void getPermissionList(Context context){
        //得到权限清单
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listpackages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (PackageInfo packageInfo : listpackages) {
            Log.e("package", packageInfo.packageName);
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                for (String permission : permissions) {
                    Log.e("package", permission);
                }
            }
        }
    }


    public static void getAllApp(Context context){
        //得到安装的所有应用的名称
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> listAppcations = packageManager.getInstalledApplications(0);
        for (ApplicationInfo info : listAppcations) {
            Log.e("info", info.loadLabel(packageManager).toString());//得到应用的名称
        }
    }
}


