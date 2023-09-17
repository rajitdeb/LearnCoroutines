package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

/**
 * Here, we'll understand the concept of Suspending Functions and Suspend Modifier
 */
class SuspendFnChapterActivity : AppCompatActivity() {
    
    private val TAG = "SUSPEND_FN_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend_fn_chapter)

        CoroutineScope(Dispatchers.IO).launch {
            // Since task1() is a suspending function, so we have called it from a Coroutine
            task1()
        }

        CoroutineScope(Dispatchers.IO).launch {
            // Since task1() is a suspending function, so we have called it from a Coroutine
            task2()
        }
    }

    /**
     * A Suspending function is capable of suspending/pausing at some point and resuming at a later point
     * Suspend function is only readable/understandable by Coroutines
     * Therefore, they can only be called from:
     * 1. a CoroutineScope[CoroutineScope, GlobalScope, MainScope, ViewModelScope]
     * 2. or another suspending function
     */
    private suspend fun task1() {
        Log.i(TAG, "STARTING TASK 1")
        // val response = getUserDetailsFromApi() // Network Call - IO Operation

        // This a predefined suspension point provided by Coroutines
        delay(1000)

        Log.i(TAG, "ENDING TASK 1")
    }

    /**
     * Here, we have used the `suspend` modifier
     * This `suspend` modifier tells the compiler that it is a suspend function
     * which may contain long-running task
     */
    private suspend fun task2() {
        Log.i(TAG, "STARTING TASK 2")

        // This a predefined suspension point provided by Coroutines
        yield()

        Log.i(TAG, "ENDING TASK 2")
    }

}