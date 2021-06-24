package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.LocalDataSource
import com.example.moviecatalogue.model.source.local.entity.FavoriteRoomDatabase
import com.example.moviecatalogue.model.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors

object Injection {

    fun provideMovieAndTVRepository(context: Context): MovieAndTVRepository {
        val favoriteRoomDatabase = FavoriteRoomDatabase.getDatabase(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(favoriteRoomDatabase.favoriteDao())
        val appExecutors = AppExecutors()
        return MovieAndTVRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}