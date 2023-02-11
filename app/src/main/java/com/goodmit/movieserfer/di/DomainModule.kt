package com.goodmit.movieserfer.di

import com.goodmit.movieserfer.BuildConfig
import com.goodmit.movieserfer.common.BASE_URL
import com.goodmit.movieserfer.domain.api.MovieService
import com.goodmit.movieserfer.domain.usecases.GetMovieDetailsUseCase
import com.goodmit.movieserfer.domain.usecases.GetMoviesUseCase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val domainModule = module {
    single { createApiInterceptor() }
    single { createLoggingInterceptor() }
    single { createOkHttpClient(get(), get()) }
    single { createRetrofit(get()) }
    single { createMovieService(get()) }
    single { GetMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
}

private fun createLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
    }

private fun createApiInterceptor(): Interceptor =
    Interceptor { chain ->
        val origRequest = chain.request()
        val origUrl = origRequest.url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val newRequest = origRequest.newBuilder()
            .url(origUrl)
            .method(origRequest.method, origRequest.body)
            .build()
        chain.proceed(newRequest)
    }

private fun createOkHttpClient(
    apiInterceptor: Interceptor,
    loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

private fun createRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private fun createMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)