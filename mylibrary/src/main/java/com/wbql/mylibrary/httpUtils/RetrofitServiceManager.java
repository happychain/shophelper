package com.wbql.mylibrary.httpUtils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public abstract class RetrofitServiceManager {
    private static final String Tag = "RetrofitServiceManager";
    private static final String BASE_URL = "";
    private String strings[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"
            , "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private final HttpLoggingInterceptor httpLoggingInterceptor
            = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.d(Tag,message);
        }
    });
    private final Interceptor httpHeadInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder();
//            requestBuilder.addHeader("AppKey", WopConstant.appkey);
//            String phone = "Boo-" + PreferenceManager.getInstance().getAppVersionName() + "-ANDROID (android;" + "brand:" + android.os.Build.BRAND + ",phone models:" + android.os.Build.MODEL + ",SDKversion :"
//                    + Build.VERSION.SDK_INT + ",OSVersion:" + android.os.Build.VERSION.RELEASE + ";WIDTH:" + WopConstant.APP_WIDTH + ",HEIGHT:" + WopConstant.APP_HEIGHT + ",DENSITY:" + WopConstant.APP_DENSITY + ")";
//            requestBuilder.addHeader("Accept-Version", WopConstant.version);
//            requestBuilder.addHeader("User-Agent", phone);
//            long dateString = System.currentTimeMillis();
//            requestBuilder.addHeader("CurTime", dateString + "");
//            requestBuilder.addHeader("Content-Type", "application/json");
//            String nonce = getRamdom();
//            requestBuilder.addHeader("Nonce", nonce);
//            String secret = "booshorturlkey!@#$%";
//            String checkSum = hmacSha1(nonce, secret);
//            requestBuilder.addHeader("CheckSum", checkSum);
//            if (Build.VERSION.SDK_INT > 13) {
//                requestBuilder.addHeader("Connection", "close");
//            }
//            String token = PreferenceManager.getInstance().getAccessToken();
//            if ((null != token) && (!token.equals(""))) {
//                requestBuilder.addHeader("Authorization", "Bearer " + token);
//            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(httpHeadInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // //强行返回true 即验证成功
                    return true;
                }
            })
            .build();

    protected Retrofit baseRetrofit() {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(getBaseUrl() == null ? BASE_URL : getBaseUrl());
        return builder.build();
    }

    protected abstract String getBaseUrl();

}
