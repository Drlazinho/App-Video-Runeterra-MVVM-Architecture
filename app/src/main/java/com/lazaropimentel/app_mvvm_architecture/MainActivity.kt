package com.lazaropimentel.app_mvvm_architecture

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lazaropimentel.app_mvvm_architecture.adapters.MainAdapter
import com.lazaropimentel.app_mvvm_architecture.databinding.ActivityMainBinding
import com.lazaropimentel.app_mvvm_architecture.repository.MainRepository
import com.lazaropimentel.app_mvvm_architecture.service.RetrofitService
import com.lazaropimentel.app_mvvm_architecture.viewmodel.main.MainViewModel
import com.lazaropimentel.app_mvvm_architecture.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    lateinit var viewModel : MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{
        openVideo(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.videoList.observe(this, { videos ->
            Log.i("Runeterra", "onStart")
            adapter.setLiveList(videos)
        })

        viewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllVideos()
    }

    private fun openVideo(link: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}