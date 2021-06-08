package com.example.moviecatalogue.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.viewmodel.DetailMovieViewModel
import com.example.moviecatalogue.viewmodel.DetailTVViewModel
import com.example.moviecatalogue.viewmodel.MovieViewModel
import com.example.moviecatalogue.viewmodel.TVViewModel

class ViewModelFactory private constructor(private val movieAndTVRepository: MovieAndTVRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideMovieAndTVRepository())
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieAndTVRepository) as T
            }
            modelClass.isAssignableFrom(TVViewModel::class.java) -> {
                TVViewModel(movieAndTVRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieAndTVRepository) as T
            }
            modelClass.isAssignableFrom(DetailTVViewModel::class.java) -> {
                DetailTVViewModel(movieAndTVRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}