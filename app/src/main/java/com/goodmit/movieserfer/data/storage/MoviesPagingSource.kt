package com.goodmit.movieserfer.data.storage

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.goodmit.movieserfer.common.getCurrentLocale
import com.goodmit.movieserfer.data.mapper.MoviesMapper
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.api.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MoviesPagingSource(
    private val service: MovieService,
    private val mapper: MoviesMapper,
) : RxPagingSource<Int, Movies.Movie>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movies.Movie>> {
        val position = params.key ?: 1

        return service.getMovies(
            category = "popular",
            page = position,
            language = getCurrentLocale().language)
            .subscribeOn(Schedulers.io())
            .map { mapper.responseToModel(it) }
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: Movies, position: Int): LoadResult<Int, Movies.Movie> {
        return LoadResult.Page(
            data = data.movies,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.totalPages) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Movies.Movie>): Int? {
        return state.anchorPosition
    }
}