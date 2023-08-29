package com.example.kotlin_learn.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums") // always use the endpoint in these method
    // get the outer most kind of variable, in this scenario is an array of album
    suspend fun getAlbums():Response<Albums>
    @GET("/albums")
    suspend fun getSpecificAlbum(@Query("userId") userId:Int):Response<Albums>
}