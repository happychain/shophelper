package com.pgyer.shophelper.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ObjectStreamException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuqiang 2021-01-08 .
 */
public class ShearPlateUtil {

    private static Context mContext;
    private static final String TAG = "ShearPlateUtil";
    private static ShearPlateUtil instance;

    private ShearPlateUtil() {

    }


    public static ShearPlateUtil getInstance(Context context) {//方法无需同步，各线程同时访问
        mContext = context;
        if (instance == null) {
            synchronized (ShearPlateUtil.class) {//在创建对象时再进行同步锁定
                if (instance == null) {
                    instance = new ShearPlateUtil();
                }
            }
        }
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }



    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getClipboardContent();
        }
    };

    public void clean(){
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    private void shearPlateListener() {
        //读取剪切板

        try {
            new Timer().schedule(task, 1000, 1200); // 1s后启动任务，每2s执行一次
            Log.e("liuqiang-->","初始化=");
        } catch (Exception e){

        }
    }

    @Nullable
    public String getClipboardContent() {
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null) {
            ClipData data = cm.getPrimaryClip();
            if (data != null && data.getItemCount() > 0) {
                ClipData.Item item = data.getItemAt(0);
                if (item != null) {
                    CharSequence sequence = item.coerceToText(mContext);
                    if (sequence != null) {
                        Log.e("liuqiang-->","剪切板内容="+sequence.toString());
                        return sequence.toString();
                    }
                }
            }
        }
        return null;
    }
}
