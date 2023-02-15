package com.goodmit.movieserfer.common


const val DATE_FORMAT = "dd MMM yyyy"
const val BASE_URL="https://api.themoviedb.org/3/movie/"

enum class MovieCategory { Popular, Top, Upcoming }

const val POPULAR_MOVIES = "popular"
const val TOP_MOVIES = "top_rated"
const val UPCOMING_MOVIES = "upcoming"