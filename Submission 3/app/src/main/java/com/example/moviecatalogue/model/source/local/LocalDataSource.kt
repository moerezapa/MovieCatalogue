package com.example.moviecatalogue.model.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.model.source.local.entity.FavoriteDao

class LocalDataSource(private val favoriteDao: FavoriteDao) {

    fun getMovieList() : DataSource.Factory<Int, MovieFavorite> = favoriteDao.getMovieList()
    fun getTVList() : DataSource.Factory<Int, TVFavorite> = favoriteDao.getTVList()

    fun getMovieFavoriteList() : DataSource.Factory<Int, MovieFavorite> = favoriteDao.getAllMovieFavorite()
    fun getTVFavoriteList() : DataSource.Factory<Int, TVFavorite> = favoriteDao.getAllTVFavorite()

    fun getMovieFavoriteDetail(movieId: Int) : LiveData<MovieFavorite> = favoriteDao.getMovieFavoriteDetail(movieId.toString())
    fun getTVFavoriteDetail(tvShowId: Int) : LiveData<TVFavorite> = favoriteDao.getTVFavoriteDetail(tvShowId.toString())

    fun insertMovieFavorite(movies: List<MovieFavorite>) = favoriteDao.insertMovieFavorite(movies)
    fun insertTVFavorite(tvShows: List<TVFavorite>) = favoriteDao.insertTVFavorite(tvShows)

    fun updateMovieFavorite(movieFavorite: MovieFavorite, newState: Boolean) {
        movieFavorite.isFavorite = newState
        favoriteDao.updateMovieFavorite(movieFavorite)
    }
    fun updateTVFavorite(tvFavorite: TVFavorite, newState: Boolean) {
        tvFavorite.isFavorite = newState
        favoriteDao.updateTVFavorite(tvFavorite)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }
}