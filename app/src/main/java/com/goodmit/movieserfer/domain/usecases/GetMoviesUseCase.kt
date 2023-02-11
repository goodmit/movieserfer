package com.goodmit.movieserfer.domain.usecases

import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.domain.api.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {
    fun execute(category: MovieCategory) = repository.getMovies(category)
}