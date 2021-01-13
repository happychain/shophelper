package com.wbql.mylibrary.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuqiang 2020-12-03 .
 */
public class AndroidScheduler implements Executor {

    private static AndroidScheduler instance;

    private final Scheduler mMainScheduler;
    private final Handler mHandler;

    private AndroidScheduler() {
        mHandler = new Handler(Looper.myLooper());
        mMainScheduler = Schedulers.from(this);
    }

    public static synchronized Scheduler mainThread() {
        if (instance == null) {
            instance = new AndroidScheduler();
        }
        return instance.mMainScheduler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }

}
