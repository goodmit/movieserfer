package com.goodmit.movieserfer.common

import androidx.paging.PagingData
import androidx.paging.PagingSource

const val BASE_URL="https://api.themoviedb.org/3/movie/"
const val IMAGE_URL="https://image.tmdb.org/t/p/w185/"
const val DEFAULT_BACKDROP_URL = "http://placehold.jp/36/cccccc/aaaaaa/480x270.png?text=Image"
const val DEFAULT_POSTER_URL = "http://placehold.jp/48/cccccc/aaaaaa/320x480.png?text=Image"

enum class MovieCategory { Popular, Top, Incoming }