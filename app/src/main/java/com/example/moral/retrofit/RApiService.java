package com.example.moral.retrofit;

import com.example.moral.model.Message;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/1/17.
 */

public interface RApiService {
    @GET("/")
    Call<ResponseBody> getApiString();

    @GET("/")
    Observable<ResponseBody> getRxApiString();
    @GET("/")
    Observable<ResponseBody> getBytes();
    @GET("api/account/user/mobile_login.json?data={mobile:18928130278,password:123456}")
    Observable<ResponseBody> getTest();

    /*
    * 获取json
    */
    @GET("json/json.txt")
    Observable<Message> getJson();
    @GET("json/json.txt")
    Observable<ResponseBody> getJson_();

}
