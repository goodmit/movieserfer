package com.goodmit.movieserfer.domain.usecases

import com.goodmit.movieserfer.common.MovieCategory

interface GetMoviesUseCase {
    fun execute(category: MovieCategory)
}