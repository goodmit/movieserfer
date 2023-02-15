package com.goodmit.movieserfer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.common.RxBus
import com.goodmit.movieserfer.common.RxEvent
import com.goodmit.movieserfer.databinding.ActivityMainBinding
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _movieListener: Disposable
    private val _rxBus : RxBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        _binding.navView.setupWithNavController(navController)
        _movieListener = _rxBus.listen(RxEvent.MovieIdRequested::class.java).subscribe {
            runMoveDetailsActivity(it.movieId)
        }
    }

    private fun runMoveDetailsActivity(movieId: Long) {
        val intent = MovieDetailsActivity.newIntent(this@MainActivity, movieId)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!_movieListener.isDisposed) _movieListener.dispose()
    }
}