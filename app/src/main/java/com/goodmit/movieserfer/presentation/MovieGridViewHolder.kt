package com.goodmit.movieserfer.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.common.DATE_FORMAT
import com.goodmit.movieserfer.common.RxBus
import com.goodmit.movieserfer.common.RxEvent
import com.goodmit.movieserfer.data.models.Movies
import com.goodmit.movieserfer.databinding.MovieViewholderBinding

class MovieGridViewHolder(
    private val binding: MovieViewholderBinding,
    private val rxBus: RxBus) : RecyclerView.ViewHolder(binding.root) {

    lateinit var currentMovie : Movies.Movie

    private val titleTextView: TextView = itemView.findViewById(R.id.movie_title)
    private val ratingTextView: TextView = itemView.findViewById(R.id.rating_title)
    private val popularityTextView: TextView = itemView.findViewById(R.id.popularity_title)
    private val dateTextView: TextView = itemView.findViewById(R.id.release_date_TextView)
    private val posterImageView: ImageView = itemView.findViewById(R.id.poster)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progress)

    init {

        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        posterImageView.layoutParams = params
        posterImageView.scaleType = ImageView.ScaleType.FIT_CENTER
        posterImageView.adjustViewBounds = true

        itemView.setOnClickListener {
            Log.d("MovieGridViewHolder", "movie ${currentMovie.movieId} was clicked")
            rxBus.send(RxEvent.MovieIdRequested(currentMovie.movieId))
        }
    }

    fun bind(movie: Movies.Movie) {
        currentMovie = movie;
        titleTextView.text = movie.title
        ("${getResourceString(itemView.context, R.string.movie_rating)}: ${movie.voteAverage}")
            .also { ratingTextView.text = it }
        ("${getResourceString(itemView.context, R.string.movie_popularity)}:" +
                " ${movie.popularity}")
            .also { popularityTextView.text = it }
        ("${getResourceString(itemView.context, R.string.movie_release_date)}:" +
                " ${DateFormat.format(DATE_FORMAT, movie.releaseDate)}")
            .also { dateTextView.text = it }


        with(movie) {
            Glide.with(binding.poster)
                .load(poster?.medium)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?,
                         target: Target<Drawable>?,
                         dataSource: DataSource?,
                         isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?,
                          target: Target<Drawable>?,
                          isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(posterImageView)
        }
    }

    private fun getResourceString(context: Context, resId: Int): String {
        return context.getString(resId)
    }

    companion object {
        fun create(parent: ViewGroup, rxBus: RxBus): MovieGridViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_viewholder,  parent,false)

            val binding = MovieViewholderBinding.bind(view)

            return MovieGridViewHolder(binding, rxBus)
        }
    }
}