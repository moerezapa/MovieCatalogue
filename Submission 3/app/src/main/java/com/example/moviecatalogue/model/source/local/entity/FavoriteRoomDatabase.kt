package com.example.moviecatalogue.model.source.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite

@Database(entities = [MovieFavorite::class, TVFavorite::class], version = 1, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteRoomDatabase::class.java, "favorite.db")
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }
}