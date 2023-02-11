package com.goodmit.movieserfer.domain.usecases

import com.goodmit.movieserfer.domain.api.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    fun execute(movieId: Long) = repository.getMovieDetails(movieId)
}