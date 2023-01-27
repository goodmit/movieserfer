package com.goodmit.movieserfer.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.databinding.MovieViewholderBinding

class MovieGridViewHolder(private val binding: MovieViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    private val titleTextView: TextView = itemView.findViewById(R.id.movie_title)
    private val posterImageView: ImageView = itemView.findViewById(R.id.poster)

    fun bind(movie: Movies.Movie) {
        titleTextView.text = movie.title
        with(movie) {
            Glide.with(binding.poster)
                .load(poster?.medium)
                .into(posterImageView)
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieGridViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_viewholder,  parent,false)

            val binding = MovieViewholderBinding.bind(view)

            return MovieGridViewHolder(
                binding
            )
        }
    }
}