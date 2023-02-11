package com.goodmit.movieserfer.presentation.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.usecases.GetMoviesUseCase
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi

class UpcomingViewModel(private val useCase: GetMoviesUseCase) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUpcomingMovies(): Flowable<PagingData<Movies.Movie>> {
        return useCase
            .execute(MovieCategory.Upcoming)
            .map { pagingData -> pagingData.filter { it.poster != null } }
            .cachedIn(viewModelScope)
    }
}