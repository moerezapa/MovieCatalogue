package com.example.moviecatalogue.model.source.local.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie_favorite")
@Parcelize
data class MovieFavorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?= null,
    @NotNull
    @ColumnInfo(name = "movieId")
    val movieId: String,
    @ColumnInfo(name = "original_title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val userScore: Double,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "poster_path")
    val imagePath: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
) : Parcelable