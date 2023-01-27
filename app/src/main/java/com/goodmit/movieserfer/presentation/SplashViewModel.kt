package com.goodmit.movieserfer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val mutableLiveData = MutableLiveData<SplashState>()
    val liveData: LiveData<SplashState> get() = mutableLiveData

    fun splashState()
    {
        GlobalScope.launch {
            delay(1500)
            mutableLiveData.postValue(SplashState.MainActivity())
        }
    }

    sealed class SplashState {
        class MainActivity : SplashState()
    }

}