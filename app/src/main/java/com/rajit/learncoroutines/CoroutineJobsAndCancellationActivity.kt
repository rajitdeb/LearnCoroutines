package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rajit.learncoroutines.databinding.ActivityCoroutineJobsAndCancellationBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.log

/**
 * Here, we will understand the concept of Job and Cancellation in Coroutines
 */
class CoroutineJobsAndCancellationActivity : AppCompatActivity() {

    private val TAG = "JOB_CANCEL_ACTIVITY"

    private lateinit var binding: ActivityCoroutineJobsAndCancellationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineJobsAndCancellationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Many a times, we want to do multiple jobs inside a coroutine
         * and managing all of them at once becomes a cumbersome task
         * So, we do it in this way:
         */
        val job = CoroutineScope(Dispatchers.Main).launch {
//            execute()
            execute1()
        }

    }

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.Main).launch {

//            Log.d(TAG, "execute: $coroutineContext")

//            val childJob = launch {// Here, coroutine by default uses Dispatchers.Main context is used from parent
//                Log.d(TAG, "execute: $coroutineContext")
//            }

            /**
             * If we want, we can also explicitly mention the coroutine Context, and
             * in that case coroutines will use that context, instead of the default parent context
             *
             * Example:
             */
//            val childJob = launch(Dispatchers.IO) {
//                Log.d(TAG, "execute: $coroutineContext")
//            }

            /**
             * Understanding Job Completion in Nested Coroutines
             */
//            Log.d(TAG, "PARENT STARTED")
//
//            val childJob = launch(Dispatchers.IO) {
//                Log.d(TAG, "CHILD STARTED")
//                delay(5000)
//                Log.d(TAG, "CHILD ENDED")
//            }
//
//            delay(3000)
//            Log.d(TAG, "PARENT ENDED") // Here, our parent's job is done

            /**
             * Understanding Job Cancellation
             */
//            Log.d(TAG, "PARENT STARTED")
//
//            val childJob = launch(Dispatchers.IO) {
//                // Here, we're catching the CancellationException which indicates that the JOB is cancelled
//                // We can try updating the UI if needed with this exception
//                try {
//                    Log.d(TAG, "CHILD STARTED")
//                    delay(5000)
//                    Log.d(TAG, "CHILD ENDED")
//                } catch (e: CancellationException) {
//                    Log.d(TAG, "CHILD JOB CANCELLED")
//                }
//            }
//
//            delay(3000)

//            Log.d(TAG, "CANCELLING CHILD JOB")
            /**
             * Whenever we cancel a JOB, the cancel function throws a CancellationException to the JOB
             * indicating the job needs to be cancelled
             *
             * NOTE: IF ANY OTHER EXCEPTION OCCURS, THEN THE ENTIRE PARENT JOB WILL BE CANCELLED
             *
             */
            // Here, we'll simulate the behaviour of USER cancelling the child JOB in the middle of processing
//            childJob.cancel()
//
//            Log.d(TAG, "PARENT ENDED") // Here, our parent's job is done

            for (i in 1..1000) {
                executeLongRunningTask()
                Log.d(TAG, i.toString())
            }
        }

        delay(1000)
        parentJob.cancel() // Here, we simulate the behaviour of USER cancelling the JOB after 1sec of starting it


        // This line of code is used to keep the coroutine in suspended state until both the parent and child job are complete
        // Then after completion, all the following statements after this will be executed normally
        parentJob.join()
        // Here, until all the coroutines inside the parent job are completed,
        // the parent job won't come to COMPLETED state
        Log.d(TAG, "PARENT COMPLETED")

    }

    private suspend fun execute1() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {
                if(isActive) { // We check if the JOB is in ACTIVE STATE, then only we execute the statements, Otherwise we skip
                    executeLongRunningTask()
                    Log.d(TAG, i.toString())
                }
            }
        }

        delay(100)
        Log.d(TAG, "Cancelling PARENT JOB")
        /**
         * Here, even after the JOB is cancelled, parentJob is still in processing, which is wastage of resources
         * So, to solve this problem, we check if the job is in ACTIVE state and only then we execute executeLongRunningTask()
         */
        parentJob.cancel()

        parentJob.join()
        Log.d(TAG, "PARENT COMPLETED")

    }

    // This function simulates a long running task
    private suspend fun executeLongRunningTask() {
        for (i in 1..1000000000L) {
        }

        delay(5000)
    }

}