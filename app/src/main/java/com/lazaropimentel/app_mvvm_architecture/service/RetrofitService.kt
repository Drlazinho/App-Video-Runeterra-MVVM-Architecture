package com.lazaropimentel.app_mvvm_architecture.service

import com.lazaropimentel.app_mvvm_architecture.model.Video
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("video.json")
    fun getAllVideos(): Call<List<Video>>

    companion object {
        private val retrofitService : RetrofitService by lazy {
            val retrofit =  Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Drlazinho/video-api/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService{
            return  retrofitService
        }
    }
}