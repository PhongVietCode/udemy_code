package com.example.kotlin_learn.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated
data class AlbumItem (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("userId")
    var userId: Int? = null,

    @SerializedName("title")
    var title: String? = null
)