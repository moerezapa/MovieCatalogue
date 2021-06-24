package com.example.moviecatalogue.model.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import com.example.moviecatalogue.vo.Resource

interface MovieAndTVDataSource {
    fun getMovieList() : LiveData<Resource<PagedList<MovieFavorite>>>
    fun getTVList() : LiveData<Resource<PagedList<TVFavorite>>>
    fun getMovieFavoriteList() : LiveData<PagedList<MovieFavorite>>
    fun getTVFavoriteList() : LiveData<PagedList<TVFavorite>>

    fun getMovieDetail(movieId: Int) : LiveData<Resource<MovieFavorite>>
    fun getTVDetail(tvId: Int) : LiveData<Resource<TVFavorite>>

    fun setMovieFavorite(movieFavorite: MovieFavorite, newState: Boolean)
    fun setTVFavorite(tvFavorite: TVFavorite, newState: Boolean)
}