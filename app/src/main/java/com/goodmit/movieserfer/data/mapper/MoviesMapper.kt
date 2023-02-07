package com.goodmit.movieserfer.data.mapper

import com.goodmit.movieserfer.common.getCurrentLocale
import com.goodmit.movieserfer.data.models.ImageEntity
import com.goodmit.movieserfer.data.models.MovieDetails
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import com.goodmit.movieserfer.domain.models.MoviesDTO
import java.text.SimpleDateFormat

private const val TMDB_DATE_FORMAT = "yyyy-mm-dd"

class MoviesMapper() {

    fun responseToModel(remoteModel: MoviesDTO): Movies {
        return with(remoteModel) {
            Movies(
                page = page,
                totalPages = totalPages,
                totalMovies = totalMovies,
                movies = movies.map {
                    Movies.Movie(
                        0,
                        it.id,
                        it.popularity,
                        it.posterPath?.let { path -> ImageEntity(path) },
                        it.backdropPath?.let { path -> ImageEntity(path) },
                        it.title,
                        it.voteAverage,
                        it.voteCount,
                        it.releaseDate.let { date ->
                            if (date.isNotEmpty()) {
                                SimpleDateFormat(TMDB_DATE_FORMAT, getCurrentLocale()).parse(date)
                            } else {
                                null
                            }
                        })
                })
        }
    }

    fun responseToMovieDetails(remoteDetails: MovieDetailsDTO): MovieDetails {
        return with(remoteDetails) {
            MovieDetails(
                id = id,
                title = title,
                posterPath = posterPath,
                budget = budget,
                genres = moveGenres(),
                overview = overview,
                releaseDate = releaseDate,
                revenue = revenue,
                status = status,
                tagline = tagline,
                voteAverage = voteAverage
            )
        }
    }
}