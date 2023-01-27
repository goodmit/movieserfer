package com.goodmit.movieserfer.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.goodmit.movieserfer.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashVM by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashVM.splashState()
        val dataObserver = Observer<SplashViewModel.SplashState> {
            goToMainScreen()
        }

        splashVM.liveData.observe(this, dataObserver)
    }

    private fun goToMainScreen() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}