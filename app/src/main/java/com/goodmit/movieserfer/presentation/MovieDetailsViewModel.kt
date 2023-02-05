package com.goodmit.movieserfer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.goodmit.movieserfer.common.MovieCategory
import com.goodmit.movieserfer.data.models.MovieDetails
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.api.MovieRepository
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MovieDetailsViewModel(private val repository: MovieRepository): ViewModel() {

    fun getMovieDetails(movieId: Long): Single<MovieDetails> {
        return repository
            .getMovieDetails(movieId)
            .cache()
    }
}