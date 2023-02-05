package com.goodmit.movieserfer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.common.getMovieCategoryId
import com.goodmit.movieserfer.data.models.MovieDetails
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.data.storage.MoviesPagingSource
import com.goodmit.movieserfer.domain.api.MovieRepository
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import io.reactivex.Flowable
import io.reactivex.Single

class MovieLocalRepositoryImpl(private val pagingSource: MoviesPagingSource) : MovieRepository {

    override fun getMovies(category : MovieCategory): Flowable<PagingData<Movies.Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource
                .apply { this.category = getMovieCategoryId(category) } }
        ).flowable
    }

    override fun getMovieDetails(movieId: Long): Single<MovieDetails> {

        TODO("Not yet implemented")
    }
}