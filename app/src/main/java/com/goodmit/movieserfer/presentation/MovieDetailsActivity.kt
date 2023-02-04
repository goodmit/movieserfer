package com.goodmit.movieserfer.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.common.DATE_FORMAT
import com.goodmit.movieserfer.common.getCurrentLocale
import com.goodmit.movieserfer.data.models.ImageEntity
import com.goodmit.movieserfer.domain.models.MovieDetailsDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Date
import java.text.SimpleDateFormat

private const val MOVIE_ID_KEY = "movie_id_key"

class MovieDetailsActivity : AppCompatActivity() {

    private var _movieId : Long = -1
    private val _movieDetailsVm by viewModel<MovieDetailsViewModel>()
    private val _mDisposable = CompositeDisposable()

    private lateinit var titleTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var posterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        _movieId = intent.getLongExtra(MOVIE_ID_KEY, -1)

        titleTextView = findViewById(R.id.movie_title)
        ratingTextView = findViewById(R.id.rating_title)
        popularityTextView = findViewById(R.id.popularity_title)
        dateTextView = findViewById(R.id.release_date_TextView)
        posterImageView = findViewById(R.id.poster)

        _movieDetailsVm.getMovieDetails(_movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<MovieDetailsDTO>() {
                override fun onSuccess(movieDetails: MovieDetailsDTO) {
                    Log.d("MovieDetailsActivity", "got movie: ${movieDetails.title}")
                    updateUI(movieDetails)
                }

                override fun onError(e: Throwable) {
                    Log.d("MovieDetailsActivity", "cannot load details for movie with id '$_movieId'")
                }
            })
    }

    private fun updateUI(movieDetails: MovieDetailsDTO) {

        titleTextView.text = movieDetails.title
        ("${getString(R.string.movie_rating)}: ${movieDetails.voteAverage}")
            .also { ratingTextView.text = it }
        ("${getString(R.string.movie_popularity)}:" +
                " ${movieDetails.revenue}")
            .also { popularityTextView.text = it }
        ("${getString(R.string.movie_release_date)}: " +
                "${DateFormat.format(DATE_FORMAT, Date.valueOf(movieDetails.releaseDate))}")
            .also { dateTextView.text = it }
        val uri = ImageEntity(movieDetails.posterPath).original
        Glide.with(posterImageView)
            .load(uri)
            .into(posterImageView)
    }

    companion object {
        fun newIntent(packageContext: Context, movieId: Long): Intent {
            return Intent(packageContext, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID_KEY, movieId)
            }
        }
    }
}