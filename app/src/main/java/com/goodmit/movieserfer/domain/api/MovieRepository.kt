package com.goodmit.movieserfer.domain.api

import androidx.paging.PagingData
import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.data.models.MovieDetails
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import com.goodmit.movieserfer.domain.models.MoviesDTO
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(category : MovieCategory): Flowable<PagingData<Movies.Movie>>
    fun getMovieDetails(movieId: Long): Single<MovieDetails>
}