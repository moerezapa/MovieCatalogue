package com.example.moviecatalogue.ui.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ItemsTvBinding
import com.example.moviecatalogue.model.TV

class TVAdapter : RecyclerView.Adapter<TVAdapter.TVViewHolder>() {

    private var listTV = ArrayList<TV>()
    private lateinit var activity: Activity

    fun setTV(TVs: List<TV>?, activity: Activity) {
        if (TVs == null) return
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

        fun bind(TV: TV) {
            with(binding) {
                tvItemTvtitle.text = TV.title
                tvItemCreator.text = TV.creator
                tvItemTvoverview.text = TV.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, TV.tvId)
                    itemView.context.startActivity(intent)
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                Glide.with(itemView.context)
                        .load(TV.imagePath)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgTvposter)
            }
        }
    }
}