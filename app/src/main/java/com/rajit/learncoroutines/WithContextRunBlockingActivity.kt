package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import com.rajit.learncoroutines.databinding.ActivityWithContextRunBlockingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Understanding the concept of withContext and runBlocking
 */
class WithContextRunBlockingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithContextRunBlockingBinding
    private val TAG = "CONTEXT_RUN_BLOCKING"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithContextRunBlockingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        GlobalScope.launch {
//            executeTask()
//        }

        /**
         * runBlocking is primarily used for sequential execution and is used in Console Applications
         * [NOT FOR ANDROID]
         * Example:
         */
//        runBlocking {
//            launch { // runBlocking provides us with a CoroutineScope, so we don't need to use a scope inside runBlocking
//                delay(1000)
//                println("WORLD")
//            }
//            println("HELLO")
//        }

    }

    private suspend fun executeTask() {
        Log.d(TAG, "BEFORE")

        /**
         * NON-BLOCKING CODE
         * The code block is executed in the following way:
         * 1. BEFORE
         * 2. AFTER
         * 3. INSIDE
         */
//        GlobalScope.launch {
//            delay(1000)
//            Log.d(TAG, "INSIDE")
//        }

        /**
         * BLOCKING CODE - withContext
         * Which means that the code block is executed sequentially, so the code block is executed in the following way:
         * 1. BEFORE
         * 2. INSIDE
         * 3. AFTER
         *
         * Basically, executed each statement and waiting for their result before executing the next statement
         *
         * NOTE: withContext() is mainly used for Context-Switching in Coroutines
         *       - which means that we can execute some part of our code in IO thread, and other in MAIN thread
         */
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.d(TAG, "INSIDE")
        }

        Log.d(TAG, "AFTER")
    }

}