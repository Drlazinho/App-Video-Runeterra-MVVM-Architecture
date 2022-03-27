package com.lazaropimentel.app_mvvm_architecture.adapters

import com.lazaropimentel.app_mvvm_architecture.R
import com.lazaropimentel.app_mvvm_architecture.databinding.ResItemVideoBinding
import com.lazaropimentel.app_mvvm_architecture.model.Video
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MainAdapter(private val onItemClicked: (Video) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private var videos = mutableListOf<Video>()

    fun setLiveList(lives: List<Video>) {

        this.videos = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemVideoBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = videos[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return videos.size
    }
}

class MainViewHolder(val binding: ResItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(video: Video, onItemClicked: (Video) -> Unit) {

        binding.title.text = video.title
        binding.author.text = video.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(video.thumbnailUrl)
            .into(binding.thumbnail)

        itemView.setOnClickListener {
            onItemClicked(video)
        }

    }

}