package com.goodmit.movieserfer.data.mapper

import android.content.Context
import com.goodmit.movieserfer.common.getCurrentLocale
import com.goodmit.movieserfer.data.models.ImageEntity
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.domain.models.MoviesDTO
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNNECESSARY_SAFE_CALL")
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
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                SimpleDateFormat("yyyy-mm-dd", getCurrentLocale()).parse(date)
                            } else {
                                null
                            }
                        })
                })
        }
    }

    fun modelToResponse(remoteModel: Movies): MoviesDTO {
        return with(remoteModel) {
            MoviesDTO(
                page = page,
                totalPages = totalPages,
                totalMovies = totalMovies,
                movies = movies.map {
                    MoviesDTO.MovieDataDTO(
                        it.movieId,
                        it.popularity,
                        it.poster?.url ?: "",
                        it.backdrop?.url ?: "",
                        it.title,
                        it.voteAverage,
                        it.voteCount,
                        it.releaseDate?.time.toString())
                })
        }
    }

}