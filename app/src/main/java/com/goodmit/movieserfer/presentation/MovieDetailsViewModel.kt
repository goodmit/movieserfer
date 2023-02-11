package com.goodmit.movieserfer.presentation

import androidx.lifecycle.ViewModel
import com.goodmit.movieserfer.data.models.MovieDetails
import com.goodmit.movieserfer.domain.usecases.GetMovieDetailsUseCase
import io.reactivex.Single

class MovieDetailsViewModel(private val useCase: GetMovieDetailsUseCase): ViewModel() {

    fun getMovieDetails(movieId: Long): Single<MovieDetails> {
        return useCase
            .execute(movieId)
            .cache()
    }
}