package com.wbql.mylibrary.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

    public static Long dataToMilliseconds(String data){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mDate = sdf.parse(data);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 获取当前毫秒直
     * @return true 表示改时间可以支付
     */
    public static boolean getCurrentMilliSecond(Long startTime,Long endTime){
        Long milliSecond = System.currentTimeMillis();
        if(startTime < milliSecond && endTime > milliSecond){
            return false;
        }
        return true;
    }

    public static boolean getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        if(dateString.equals("22:19:00")){
            Log.e("liuqiang-->","111");
            return true;
        }else{
            Log.e("liuqiang-->",dateString);
            return false;
        }

    }

}
