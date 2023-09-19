package com.rajit.learncoroutines.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val TAG: String = "MainViewModel"

    init {
        
        viewModelScope.launch { // viewModelScope indicates lifecycle of the coroutine to be that of the viewModel
            delay(500)
            Log.d(TAG, "Hello ViewModel")
        }
        
    }

    // This method is called whenever the viewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel Destroyed")

    }

}