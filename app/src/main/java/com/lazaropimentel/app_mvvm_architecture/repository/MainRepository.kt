package com.lazaropimentel.app_mvvm_architecture.repository

import com.lazaropimentel.app_mvvm_architecture.service.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllVideos() = retrofitService.getAllVideos()

}