package com.example.moviecatalogue.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R

object LoadImageHelper {

    fun showImage(context: Context, imgSource: String, imageView: ImageView) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w185$imgSource")
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(imageView)
    }
}