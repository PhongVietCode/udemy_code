package com.example.appwearos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("")
    Call<String> listRepos(@Path("user") String user);
//    @POST("/showmessage")
//    Call<String> showMessage()
}
