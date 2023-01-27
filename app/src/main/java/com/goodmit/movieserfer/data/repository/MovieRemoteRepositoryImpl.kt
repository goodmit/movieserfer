package com.goodmit.movieserfer.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.data.storage.MovieDatabase
import com.goodmit.movieserfer.data.storage.MoviesRemoteMediator
import com.goodmit.movieserfer.domain.api.MovieRepository
import io.reactivex.Flowable

class MovieRemoteRepositoryImpl(
    private val context: Context,
    private val remoteMediator: MoviesRemoteMediator
): MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(): Flowable<PagingData<Movies.Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { MovieDatabase.getInstance(context).moviesDao().selectAll() }
        ).flowable
    }
}