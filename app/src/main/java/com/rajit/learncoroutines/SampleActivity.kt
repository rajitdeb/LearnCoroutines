package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajit.learncoroutines.databinding.ActivitySampleBinding

/**
 * This activity is used to simulate the behaviour of viewModel destruction
 */
class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}