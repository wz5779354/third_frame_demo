package com.letmecook.letmecook.myapplication.net;



import com.letmecook.letmecook.myapplication.bean.HeaderBean;
import com.letmecook.letmecook.myapplication.bean.ResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by azy on 2017/6/14.
 */

public interface ApiService {


    /**
     * 首次启动App
     *
     * @return
     */
    @GET("other/version")
    Observable<ResultBean> otherVersion();

    /**
     * 首页banner
     *
     * @return
     */
    @GET("welcome/banners3")
    Observable<ResultBean<HeaderBean>> banner();

    /**
     *
     * 登录
     */

    @Headers({"content-type:application/json","authorization:''"})
    @POST("member/signin2")
    Observable<ResultBean> signin(@Body RequestBody body);

    @Headers({"content-type:application/json","authorization:''"})
    @POST("member/signin2")
    Observable<ResultBean> signin2(@Body Map<String,Object> params);
}
