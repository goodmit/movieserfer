package com.goodmit.movieserfer.presentation

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.common.DATE_FORMAT
import com.goodmit.movieserfer.data.models.ImageEntity
import com.goodmit.movieserfer.data.models.MovieDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Date

private const val MOVIE_ID_KEY = "movie_id_key"

class MovieDetailsActivity : AppCompatActivity() {

    private var _movieId : Long = -1
    private val _movieDetailsVm by viewModel<MovieDetailsViewModel>()

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var revenueTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var taglineTextView: TextView
    private lateinit var posterImageView: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _movieId = intent.getLongExtra(MOVIE_ID_KEY, -1)

        titleTextView = findViewById(R.id.movie_title)
        descriptionTextView = findViewById(R.id.description_title)
        genreTextView = findViewById(R.id.movie_genre)
        ratingTextView = findViewById(R.id.rating_title)
        revenueTextView = findViewById(R.id.revenue_title)
        dateTextView = findViewById(R.id.release_date_TextView)
        statusTextView = findViewById(R.id.movie_status)
        taglineTextView = findViewById(R.id.movie_tagline)
        posterImageView = findViewById(R.id.poster)
        progressBar = findViewById(R.id.progress)

        _movieDetailsVm.getMovieDetails(_movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MovieDetails>() {
                override fun onSuccess(movieDetails: MovieDetails) {
                    Log.d("MovieDetailsActivity", "got movie: ${movieDetails.title}")
                    updateUI(movieDetails)
                }

                override fun onError(e: Throwable) {
                    Log.d("MovieDetailsActivity", "cannot load details for movie with id '$_movieId'")
                }
            })
    }

    private fun updateUI(movieDetails: MovieDetails) {

        titleTextView.text = movieDetails.title

        ("${getString(R.string.movie_overview)}: ${movieDetails.overview}")
            .also { descriptionTextView.text = it }
        ("${getString(R.string.movie_genre)}: ${movieDetails.genres}")
            .also { genreTextView.text = it }
        ("${getString(R.string.movie_rating)}: ${movieDetails.voteAverage}")
            .also { ratingTextView.text = it }
        ("${getString(R.string.movie_revenue)}:" +
                " ${movieDetails.revenue}")
            .also { revenueTextView.text = it }
        ("${getString(R.string.movie_release_date)}: " +
                "${DateFormat.format(DATE_FORMAT, Date.valueOf(movieDetails.releaseDate))}")
            .also { dateTextView.text = it }
        ("${getString(R.string.movie_status)}: ${movieDetails.status}")
            .also { statusTextView.text = it }
        ("${getString(R.string.movie_tagline)}: ${movieDetails.tagline}")
            .also { taglineTextView.text = it }

        val uri = ImageEntity(movieDetails.posterPath).original
        Glide.with(posterImageView)
            .load(uri)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?, model: Any?,
                    target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    return updateProgressBar(false)
                }

                override fun onLoadFailed(e: GlideException?, model: Any?,
                    target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    return updateProgressBar(false)
                }
            })
            .into(posterImageView)
    }

    private fun updateProgressBar(isActive: Boolean) : Boolean {
        progressBar.visibility = if (isActive) View.VISIBLE else View.GONE
        return isActive
    }

    companion object {
        fun newIntent(packageContext: Context, movieId: Long): Intent {
            return Intent(packageContext, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID_KEY, movieId)
            }
        }
    }
}