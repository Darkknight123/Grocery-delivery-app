package com.example.dashfoods;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String,String>map);
    @POST("/signUp")
    Call<Void>executeSignUp(@Body HashMap<String, String>map);


}
