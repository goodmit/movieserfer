package com.goodmit.movieserfer.di

import com.goodmit.movieserfer.data.mapper.MoviesMapper
import com.goodmit.movieserfer.data.repository.MovieLocalRepositoryImpl
import com.goodmit.movieserfer.data.repository.MovieRemoteRepositoryImpl
import com.goodmit.movieserfer.data.storage.MovieDatabase
import com.goodmit.movieserfer.data.storage.MoviesRemoteMediator
import com.goodmit.movieserfer.domain.api.MovieRepository
import org.koin.dsl.module

val dataModule = module {
    single { MoviesMapper() }
    single { MoviesRemoteMediator(get(), get(), get()) }
    single<MovieRepository> { MovieRemoteRepositoryImpl(get(), get()) }
}