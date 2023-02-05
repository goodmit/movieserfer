package com.goodmit.movieserfer.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.goodmit.movieserfer.data.mapper.Converters
import com.goodmit.movieserfer.data.models.*

@Database(
    entities = [Movies.Movie::class,
               Movies.MovieRemoteKeys::class,
               MovieDetails::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun moviesDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
    abstract fun movieDetailsDao(): MovieDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        ).also {
                            INSTANCE = it
                        }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MovieDatabase::class.java, "TMDB.db")
                .allowMainThreadQueries()
                .build()
    }
}