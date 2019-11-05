package com.example.a1105;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SimpleDemoApiService {
    @GET("bins/8ru3p")
    Call<ResponseBody>getJereChenBlog();

}
