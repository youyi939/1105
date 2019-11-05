package com.example.a1105;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class SimpleDemoApiWrapper {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.myjson.com/";
    public static Retrofit getRetrofitInstance(){


        if (retrofit == null){

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(50,TimeUnit.SECONDS)
                    .connectTimeout(1,TimeUnit.MINUTES)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
