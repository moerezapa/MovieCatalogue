package com.example.moviecatalogue.di

import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.RemoteDataSource

object Injection {

    fun provideMovieAndTVRepository(): MovieAndTVRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieAndTVRepository.getInstance(remoteDataSource)
    }
}