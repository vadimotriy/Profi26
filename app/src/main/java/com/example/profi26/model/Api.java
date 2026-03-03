package com.example.profi26.model;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Api {
    @POST("collections/users/auth-with-password")
    Call<ResponseBody> auth(@Body Map<String, String> data);
}