package com.example.moviecatalogue.model.source.local.entity

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = MovieFavorite::class)
    fun insertMovieFavorite(movieFav: List<MovieFavorite>)
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = TVFavorite::class)
    fun insertTVFavorite(tvFav: List<TVFavorite>)

    @Update
    fun updateMovieFavorite(movieFav: MovieFavorite)
    @Update
    fun updateTVFavorite(tvFav: TVFavorite)

    @Delete
    fun deleteMovieFavorite(movieFav: MovieFavorite)
    @Delete
    fun deleteTVFavorite(tvFav: TVFavorite)

    @Query("SELECT * from movie_favorite")
    fun getMovieList():  DataSource.Factory<Int, MovieFavorite>
    @Query("SELECT * from tv_favorite")
    fun getTVList():  DataSource.Factory<Int, TVFavorite>

    @Query("SELECT * from movie_favorite WHERE isFavorite = 1") // ngambil data yg favorit aja
    fun getAllMovieFavorite():  DataSource.Factory<Int, MovieFavorite>
    @Query("SELECT * from tv_favorite WHERE isFavorite = 1")  // ngambil data yg favorit aja
    fun getAllTVFavorite():  DataSource.Factory<Int, TVFavorite>

    @Query("SELECT * FROM movie_favorite WHERE movieId = :movieId")
    fun getMovieFavoriteDetail(movieId: String): LiveData<MovieFavorite>
    @Query("SELECT * FROM tv_favorite WHERE tvId = :tvId")
    fun getTVFavoriteDetail(tvId: String): LiveData<TVFavorite>
}