package com.example.moviecatalogue.ui.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ItemsTvBinding
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import com.example.moviecatalogue.utils.LoadImageHelper

class TVAdapter : RecyclerView.Adapter<TVAdapter.TVViewHolder>() {

    private var listTV = ArrayList<TVResponse>()
    private lateinit var activity: Activity

    fun setTV(TVs: List<TVResponse>, activity: Activity) {
        this.listTV.clear()
        this.listTV.addAll(TVs)
        this.activity = activity
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val itemsAcademyBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val course = listTV[position]
        holder.bind(course)
        holder.setActivity(activity)
    }

    override fun getItemCount(): Int = listTV.size

    class TVViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var activity: Activity

        fun setActivity(activity: Activity) {
            this.activity = activity
        }

        fun bind(TV: TVResponse) {
            with(binding) {
                tvItemTvtitle.text = TV.title
                tvItemCreator.text = "On Air: ${TV.releaseDate}"
                tvItemTvoverview.text = TV.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, TV.tvId)
                    itemView.context.startActivity(intent)
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                LoadImageHelper.showImage(itemView.context, TV.imagePath, imgTvposter)
            }
        }
    }
}