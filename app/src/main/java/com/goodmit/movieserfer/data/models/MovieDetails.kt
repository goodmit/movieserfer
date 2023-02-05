package com.goodmit.movieserfer.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie_details")
data class MovieDetails(
    @PrimaryKey
    val id: Long,
    val title: String,
    val posterPath: String,
    val budget: Int,
    val genres: String,
    val overview: String,
    val releaseDate: String,
    val revenue: Int,
    val status: String,
    val tagline: String,
    val voteAverage: Double) : Parcelable