package com.goodmit.movieserfer.domain.api

import com.goodmit.movieserfer.BuildConfig
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import com.goodmit.movieserfer.domain.models.MoviesDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("{category}")
    fun getMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "ru-RU"
    ): Single<MoviesDTO>

/*
    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<MoviesDTO>

    @GET("upcoming")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<MoviesDTO>
*/

    @GET("{movieId}")
    fun getMovieDetailsById(
        @Path("movieId") movieId: Int
        //@Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Single<MovieDetailsDTO>
}