package com.goodmit.movieserfer.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

import java.util.*

@Parcelize
data class Movies(
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalMovies: Int = 0,
    val movies: List<Movie>
) : Parcelable {

    @IgnoredOnParcel
    val endOfPage = totalPages == page

    @Parcelize
    @Entity(tableName = "movies")
    data class Movie(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val movieId: Long,
        val popularity: Double,
        val poster: ImageEntity?,
        val backdrop: ImageEntity?,
        val title: String,
        val voteAverage: Double,
        val voteCount: Int,
        val releaseDate: Date?
    ) : Parcelable

    @Parcelize
    @Entity(tableName = "movie_remote_keys")
    data class MovieRemoteKeys(
        @PrimaryKey val movieId: Long,
        val prevKey: Int?,
        val nextKey: Int?
    ) : Parcelable
}