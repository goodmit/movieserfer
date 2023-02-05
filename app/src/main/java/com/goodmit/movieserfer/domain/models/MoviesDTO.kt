package com.goodmit.movieserfer.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MoviesDTO(
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalMovies: Int,
    @SerializedName("results")
    val movies: List<MovieDataDTO>
) {
    data class MovieDataDTO(
        val id: Long,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("release_date")
        val releaseDate: String
    )
}