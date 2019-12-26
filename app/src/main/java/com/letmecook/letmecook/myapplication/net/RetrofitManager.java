package com.letmecook.letmecook.myapplication.net;

import android.net.Uri;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.letmecook.letmecook.myapplication.base.BaseApp;
//import com.letmecook.letmecook.myapplication.net.gsonFactory.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理类
 *
 * @author yang
 */
public enum RetrofitManager {
    /**
     * 枚举
     */
    INSTANCE;

    //    外包测试服务器
//    public static final String BASEURL = "http://demo.yunmofo.cn:8085/letmecook/index.php/api/";
//    public static final String BASEH5URL = "http://demo.yunmofo.cn:8085/letmecook/webApp/";
//测试服务器
//    public static final String BASEURL = "http://18.221.73.102/index.php/api/";
//    public static final String BASEH5URL = "http://18.221.73.102/webApp/";
    //新测试服务器
/*    public static final String BASEURL = "http://39.107.85.204/index.php/api/";
    public static final String BASEH5URL = "http://39.107.85.204/webApp/";*/
//    正是服务器
    public static final String BASEURL = "http://www.letmecook.net/index.php/api/";
    public static final String BASEH5URL = "http://www.letmecook.net/webApp/";
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;

    public Retrofit net() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(getOkHttp())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 缓存配置
     */
    File cacheFile = getCacheDir();
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(BaseApp.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(BaseApp.getAppContext())) {
                int maxAge = 0 * 60;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached,  max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };


    private OkHttpClient getOkHttp() {
        if (mOkHttpClient == null) {
            final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient().newBuilder().cache(cache)
                    .addNetworkInterceptor(httpLoggingInterceptor)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Request sessionRequest = request.newBuilder()
                                    .header("content-type", "application/json")
                                    //todo  这里对登录接口单独传""
                                    .header("authorization", (request.toString().contains("signup") || request.toString().contains("signin") || request.toString().contains("send_sms")) ? "":"")
                                    .header("platform", "1")
                                    /*   .header("language", SPUtils.getInstance().getString(C.LANGUAGE, "en").equals("zh") ? "zh" : "en")
                                       .header("latitude", SPUtils.getInstance().getString(C.LATITUDE, "0"))
                                       .header("longitude", SPUtils.getInstance().getString(C.LONGITUDE, "0"))
                                       .header("versionName", MyTools.getVersionName(BaseApp.getAppContext()))*/
                                    .build();
                            Response response = chain.proceed(sessionRequest);
                            return response;
                        }
                    })
                    .build();
        }
        return mOkHttpClient;
    }


    /**
     * 获取缓存的地址
     *
     * @return
     */
    public static File getCacheDir() {
        File cacheDir = new File(Environment.getExternalStorageDirectory().getPath() +
                File.separator + "DongYa");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }
}