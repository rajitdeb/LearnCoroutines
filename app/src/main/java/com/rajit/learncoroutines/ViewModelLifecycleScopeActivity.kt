package com.rajit.learncoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rajit.learncoroutines.databinding.ActivityViewModelLifecycleScopeBinding
import com.rajit.learncoroutines.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Understanding the concept of ViewModelScope and LifecycleScope
 */
class ViewModelLifecycleScopeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewModelLifecycleScopeBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelLifecycleScopeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * ViewModel Instantiation
         *
         * ViewModel object is destroyed whenever the activity is destroyed
         */
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        /**
         * LifecycleScope indicates the lifecycle of the coroutines with respect to the Activity/Fragment it is attached to
         * Meaning, lifecycleScope has the same lifecycle as the activity/fragment it is attached to.
         */
        lifecycleScope.launch {
            delay(2000)
            startActivity(
                Intent(
                    this@ViewModelLifecycleScopeActivity,
                    SampleActivity::class.java
                )
            )
            finish()
        }

    }
}