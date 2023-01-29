package com.goodmit.movieserfer.di

import com.goodmit.movieserfer.presentation.MainViewModel
import com.goodmit.movieserfer.presentation.SplashViewModel
import com.goodmit.movieserfer.presentation.ui.incoming.UpcomingViewModel
import com.goodmit.movieserfer.presentation.ui.popular.PopularFragment
import com.goodmit.movieserfer.presentation.ui.popular.PopularViewModel
import com.goodmit.movieserfer.presentation.ui.top.TopViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fragment { PopularFragment() }
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { PopularViewModel(get()) }
    viewModel { TopViewModel(get()) }
    viewModel { UpcomingViewModel(get()) }
}