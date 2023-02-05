package com.goodmit.movieserfer.data.models

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movies.Movie>)

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Movies.Movie>

    @Query("DELETE FROM movies")
    fun clearMovies()
}

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    fun movieDetailsById(movieId: Long): MovieDetails

    @Query("DELETE FROM movie_details")
    fun clearMovieDetails()
}

@Dao
interface MovieRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<Movies.MovieRemoteKeys>)

    @Query("SELECT * FROM movie_remote_keys WHERE movieId = :movieId")
    fun remoteKeysByMovieId(movieId: Long): Movies.MovieRemoteKeys?

    @Query("DELETE FROM movie_remote_keys")
    fun clearRemoteKeys()
}