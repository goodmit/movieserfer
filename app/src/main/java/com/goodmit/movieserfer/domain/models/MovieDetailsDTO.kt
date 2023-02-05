package com.goodmit.movieserfer.domain.models


import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
//    @SerializedName("adult")
//    val adult: Boolean,
//    @SerializedName("backdrop_path")
//    val backdropPath: String,
//    @SerializedName("belongs_to_collection")
//    val belongsToCollection: Any,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<GenreDTO>,
//    @SerializedName("homepage")
//    val homepage: String,
//    @SerializedName("imdb_id")
//    val imdbId: String,
//    @SerializedName("original_language")
//    val originalLanguage: String,
//    @SerializedName("original_title")
//    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    data class GenreDTO(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    fun moveGenres() : String = with(StringBuilder()) {
        for(genre in genres) {
            append(genre.name)
            append(", ")
        }
        trim()
        deleteCharAt(length - 1)
        toString()
    }
}