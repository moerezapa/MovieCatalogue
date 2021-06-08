package com.example.moviecatalogue.model.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVResponse(
    @SerializedName("id")
    val tvId: String,
    @SerializedName("original_name")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val userScore: Double,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("first_air_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val imagePath: String
) : Parcelable