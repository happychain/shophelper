package com.pgyer.shophelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if(!isIgnoringBatteryOptimizations()){
//                requestIgnoreBatteryOptimizations();
//            }
//        }
        //you have to start the service once.


        if (!ShopHelperService.isStart()) {
            try {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } catch (Exception e) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                e.printStackTrace();
            }
        }
    }

//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private boolean isIgnoringBatteryOptimizations() {
//        boolean isIgnoring = false;
//        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        if (powerManager != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
//            }
//        }
//        return isIgnoring;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void requestIgnoreBatteryOptimizations() {
//        try {
//            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent,1001);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1001){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if(!isIgnoringBatteryOptimizations()){
//                    MobileManufacturer.getInstance().startMoblieSetting(this);
//                }
//            }
//        }
//    }


    /**
     * 用户手动开启辅助功能权限
     * @param mContext
     * @param clazz
     * @return
     *
     *
     */

    public  boolean isAccessibilitySettingsOn(Context mContext, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        final String service = mContext.getPackageName() + "/" + clazz.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
