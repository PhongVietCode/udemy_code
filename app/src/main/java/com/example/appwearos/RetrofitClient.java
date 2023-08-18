package com.example.appwearos;

import androidx.annotation.Nullable;

import retrofit2.Retrofit;

public class RetrofitClient {
    public RetrofitClient(@Nullable String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
    }
}
