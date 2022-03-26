package com.lazaropimentel.app_mvvm_architecture.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazaropimentel.app_mvvm_architecture.model.Video
import com.lazaropimentel.app_mvvm_architecture.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val videoList = MutableLiveData<List<Video>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllVideos(){
        val request = repository.getAllVideos()
        request.enqueue(object : Callback<List<Video>>{
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                //Quando houver usa resposta
                videoList.postValue(response.body())

            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                //Quando houver uma falha
                errorMessage.postValue(t.message)

            }
        })
    }
}