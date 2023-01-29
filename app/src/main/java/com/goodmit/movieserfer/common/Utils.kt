package com.goodmit.movieserfer.common

import android.os.Build
import android.os.LocaleList
import java.util.*

fun getCurrentLocale() : Locale =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LocaleList.getDefault().get(0)
    } else {
        Locale.getDefault()
    }

fun getMovieCategoryId(category: MovieCategory) =
    when (category) {
        MovieCategory.Popular -> POPULAR_MOVIES
        MovieCategory.Upcoming -> UPCOMING_MOVIES
        MovieCategory.Top -> TOP_MOVIES
    }
