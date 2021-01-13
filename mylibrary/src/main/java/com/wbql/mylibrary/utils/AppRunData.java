package com.wbql.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.DisplayMetrics;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * app运行状态数据
 * context变量、屏幕宽高、设备类型等信息
 */
public class  AppRunData {
	public static String PHOTO_MODE="";
	/**
	 * 获取手机品牌型号
	 */
	public static Context mContext;
	/**
	 * 屏幕宽度
	 */
	public static int SCREEN_WIDTH = 0;
	/**
	 * 屏幕高度
	 */
	public static int SCREEN_HEIGHT = 0;
	/**
	 *屏幕像素
	 */
	public static float SCREEN_DENSITY = 0;
	/**
	 * 设备型号
	 */
	public static String DEVICE_MODE = "";

	/**
	 * 操作系统版本
	 */
	public static String OS_VERSION = "";

	/**
	 * SDK版本
	 */
	public static String SDK_VERSION = "";

	/**
	 * 软件版本 例如：v1.0
	 */
	public static String VERSION_NAME = "";
	/**
	 * 软件版本 例如：15
	 */
	public static int VERSION_CODE = 1;
	/**
	 * 应用包名
	 */
	public static String PACKAGE_NAME = "";
	/**
	 * 应用是否运行在前台，可见
	 */
	public static Boolean IS_FORECEGROUND = true;

	/**
	 * 所有activity集合
	 */
	public static List<WeakReference<Activity>> ACTIVITY_LIST= new ArrayList<WeakReference<Activity>>();

	DisplayMetrics metrics=new DisplayMetrics();


	public static void init(Activity activity) {

		// 如果已经初始化成功
		if (activity == null) {
			return;
		}
		if (SCREEN_WIDTH > 0 && VERSION_NAME.length() > 0) {
			return;
		}
		// 获得软件版本
		PackageManager manager = activity.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(
					activity.getPackageName(), 0);
			PACKAGE_NAME = info.packageName;
			VERSION_NAME = info.versionName;
			VERSION_CODE = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		PHOTO_MODE= Build.BRAND;
		// OS版本
		OS_VERSION = Build.VERSION.RELEASE;
		// 机器型号
		DEVICE_MODE = Build.MODEL;
		// SDK版本
		SDK_VERSION = String.valueOf(Build.VERSION.SDK_INT);

		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 窗口的宽度
		int screenWidth = dm.widthPixels;
		SCREEN_WIDTH = screenWidth;
		// 窗口高度
		int screenHeight = dm.heightPixels;
		SCREEN_HEIGHT = screenHeight;
		SCREEN_DENSITY = dm.density;
	}
}
