package com.example.moviecatalogue.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.viewmodel.FavoriteViewModel
import com.example.moviecatalogue.viewmodel.MovieAndTVViewModel

class ViewModelFactory (private val movieAndTVRepository: MovieAndTVRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieAndTVViewModel::class.java) -> MovieAndTVViewModel(movieAndTVRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(movieAndTVRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(movieAndTVRepository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieAndTVRepository(context))
            }
    }
}