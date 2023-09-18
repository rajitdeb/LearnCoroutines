package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Here, we will understand the concept of:
 * 1. Coroutine Builders
 * 2. Launch
 * 3. Async
 */
class LaunchAsyncActivity : AppCompatActivity() {

    private val TAG = "LAUNCH_ASYNC_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_async)

        /**
         * 1. Coroutine Builders - Functions that help us in creating coroutines.
         * 2. Launch - is used when we don't care about the result being returned, and more concerned about the execution
         *              - Returns a @Job object
         * 3. Async & Await - are used when we do care about the result being returned
         *                  - Returns a @Deferred object of type of the last statement in the coroutine block
         *
         * Example:
         */
//        CoroutineScope(Dispatchers.IO).launch { // This launch function is used to create a coroutine, the usual way
//            // Some code written here
//        }


        /**
         * The above code returns a job object which can be used to cancel, resume or manage coroutines.
         * Example:
         */
        val job = CoroutineScope(Dispatchers.IO).launch {
            // some code
            printFollowers()
        }

    }

    // This function calls the suspended function `getFbFollowers()` and print it in a Log statement
    private suspend fun printFollowers() {
        /**
         * In the following way, we make use of launch builders to execute our coroutines
         * But whenever we are required some result returned by our coroutine, we make use of async builders instead of launch
         *
         * Example:
         */
//        var fbFollowers = 0
//
//        val job = CoroutineScope(Dispatchers.IO).launch {
//            fbFollowers = getFbFollowers()
//        }
//
//        // This line ensures that all the statements following this line will be executed only after the coroutine is completed
//        job.join()
//        Log.d(TAG, "printFollowers: $fbFollowers")

        /**
         * But to achieve the exact same thing, we can write a much more cleaner code
         * With async and await
         *
         * Example:
         */
        val job = CoroutineScope(Dispatchers.IO).async {
            /**
             * the async function returns a Deferred object
             * and its type is of the last statement present in the block
             * in our case getFbFollowers() which returns a type Int
             */
            getFbFollowers()
        }

        /** And here we use await function to get the result after completion of the coroutine */
        Log.d(TAG, "printFollowers: ${job.await()}")

        /**
         * Now, a very common mistake/problem that arises while using the launch code is that:
         * We are ideally to use different Coroutines for different tasks:
         * Like for example:
         * We use a CoroutineScope for getFbFollowers(), and another CoroutineScope for getInstaFollowers()
         * That way we'll be able to parallely execute both the API calls in same time, thereby saving time
         *
         * But the mistake that is done is this:
         */
//        CoroutineScope(Dispatchers.IO).launch {
//            // If we write the code this way, we're doing the same thing(getting followers from Insta and FB
//            // But wasting more time
//            // Here, the coroutine will first execute FB API call, and wait for 3Sec, for that time the coroutine will be in suspended state
//            // Then, the coroutine will execute Insta API call, and again wait for 3Sec, and the coroutine will be in suspended state till we get the result
//            val fbFollowers = getFbFollowers()
//            val instaFollowers = getInstaFollowers()
//
//            Log.d(TAG, "printFollowers: FB - $fbFollowers, Insta - $instaFollowers")
//        }

        /**
         * So, instead of doing it the above way, we rather do it like this:
         *
         */
        CoroutineScope(Dispatchers.IO).launch {
            // Here, async is used because we're expecting a result from the functions
            // This way we're executing both the functions in parallely, which means both of them will take 3Sec instead of 6Sec
            val fbFollowers = async { getFbFollowers() }
            val instaFollowers = async { getInstaFollowers() }

            // Here, we're waiting for both the results to be available, before printing anything
            Log.d(TAG, "printFollowers: FB - ${fbFollowers.await()}, Insta - ${instaFollowers.await()}")

            /** This way the code is much more cleaner and readable, ALWAYS USE IT LIKE THIS */
        }

    }

    // This function mimics an API call by delaying the response to 5sec and then returning a number
    private suspend fun getFbFollowers(): Int {
        delay(3000)
        return 54
    }

    // This function mimics an API call by delaying the response to 5sec and then returning a number
    private suspend fun getInstaFollowers(): Int {
        delay(3000)
        return 113
    }

}