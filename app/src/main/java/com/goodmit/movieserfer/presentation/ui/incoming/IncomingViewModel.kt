package com.goodmit.movieserfer.presentation.ui.incoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncomingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is incoming Fragment"
    }
    val text: LiveData<String> = _text
}