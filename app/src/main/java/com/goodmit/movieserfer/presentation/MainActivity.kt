package com.goodmit.movieserfer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.goodmit.movieserfer.R
import com.goodmit.movieserfer.common.RxBus
import com.goodmit.movieserfer.common.RxEvent
import com.goodmit.movieserfer.databinding.ActivityMainBinding
import com.goodmit.movieserfer.presentation.ui.MovieDetailsFragment
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _movieListener: Disposable
    private val _rxBus : RxBus by inject()
    //private val mainVM by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        //val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val appBarConfiguration = AppBarConfiguration(
          //  setOf(
            //    R.id.navigation_popular, R.id.navigation_top, R.id.navigation_incoming
            //)
        //)
        _binding.navView.setupWithNavController(navController)

        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)

        _movieListener = _rxBus.listen(RxEvent.MovieIdRequested::class.java).subscribe {
            val fragment = MovieDetailsFragment.newInstance(it.movieId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!_movieListener.isDisposed) _movieListener.dispose()
    }
}