package com.rajit.learncoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rajit.learncoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope

/**
 *
 * COROUTINES - CO stands for Co-operative || ROUTINES stands for Functions
 * COROUTINES mean 'Co-operative Functions'
 *
 * Coroutines are a framework that efficiently manages the threads in a system
 * For executing different tasks
 *
 **/

class MainActivity : AppCompatActivity() {

    private val TAG = "LEARN COROUTINES"

    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         *
         * Coroutines - Basic Concept
         * 1. Program - A set of instructions for the device/computer
         * 2. Process - Whenever we run a program, a process is created (an actual instance of program)
         *              - All the instructions are executed within this process
         *              - Everytime a new instance of a program is created, a new process is created with it
         *
         */

        /**
         * Properties (Components) of Process:
         * 1. Process ID
         * 2. State
         * 3. Memory
         * 4. Handles for performing Network, File System operations, etc.
         *
         */

        /** PROCESS runs on a Thread (also called Thread of Execution) */

        /**
         * Problems that Coroutine solves:
         * 1. Every system has a limitation of a set number of Threads
         *      - Basically, a system can only create a limited number of Threads
         *
         * 2. Coroutines make use of the Thread Pool and re-uses the Threads for different operations
         *    and efficiently manages them.
         *
         * 3. One thread can have multiple coroutines, meaning One thread can run many coroutines
         *
         * 4. Coroutines are cheaper as they can reuse threads instead of creating new threads for every task
         *      - By creating multiple coroutines and run them on a thread
         */

        // All the following code is being executed in the MAIN thread
        binding.counterTv.text = count.toString()

        binding.updateCounterBtn.setOnClickListener {
            updateCounter()
        }

        Log.i(TAG, "onCreate: Thread Name: ${Thread.currentThread().name}")

        binding.longRunningTaskBtn.setOnClickListener {
            /**
             * This freezes the UI for some time while executing the long-running task
             * executeLongRunningTask()
             *
             *
             * So to solve the problem, we first try using the Thread approach
             * we'll make use of threads to execute this long running task
             * thread(start = true) { // start parameter ensures that the thread is immediately started
             *
             *    // each thread takes roughly 2MB of space
             *    executeLongRunningTask()
             * }
             *
             * PROBLEM HERE : "Context switching is very complex while using Threads directly"
             */

            /**
             * Now, we try the Coroutine approach
             * and also solve the Context Switching problem
             *
             * NOTE:
             * 1. Coroutines are just like threads (lightweight threads) but NOT THREADS
             * 2. Coroutines run on TOP OF THREADS
             *
             * For defining Coroutines, we need two things:
             * 1. Coroutine Scope - which defines the lifetime of our Coroutine
             * 2. Coroutine Context - which defines the Threads in which our coroutines will execute
             *
             * TYPES OF COROUTINE SCOPES:
             * 1. lifecycleScope()
             * 2. CoroutineScope()
             * 3. viewModelScope()
             *
             * TYPES OF COROUTINE CONTEXT:
             * 1. Dispatchers.IO
             * 2. Dispatchers.MAIN
             * 3. Dispatchers.Default
             *
             * ** Here, Dispatchers mean that we're dispatching our coroutines to respective threads (IO, MAIN, etc.)
             */
            CoroutineScope(Dispatchers.IO).launch { // This lasts as long as the view lasts
                Log.i(TAG, "coroutineScope: Thread Name: ${Thread.currentThread().name}")
                executeLongRunningTask()
            }

            GlobalScope.launch(Dispatchers.Main) {// This is application wide scope
                Log.i(TAG, "globalScope: Thread Name: ${Thread.currentThread().name}")
            }

            MainScope().launch(Dispatchers.Default) {// This is always attached to app's MainActivity
                Log.i(TAG, "MainScope: Thread Name: ${Thread.currentThread().name}")
            }

            Log.i(TAG, "onLongBtnClick: Thread Name: ${Thread.currentThread().name}")

        }

        binding.nextChapterBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SuspendFnChapterActivity::class.java))
        }

        binding.launchAsyncBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LaunchAsyncActivity::class.java))
        }

    }

    private fun executeLongRunningTask() {
        Log.i(TAG, "executeLongRunningTask: Thread Name: ${Thread.currentThread().name}")
        for(i in 1..1000000000L) {
            // simulate the behaviour of a Long-running task
        }
    }

    // Updates the counter on button click
    private fun updateCounter() {
        /**
         * All the code here is being executed in the MAIN Thread
         * Also called UI Thread in context of Android
         *
         * In Android, we have the concept of:
         * 1. Looper - it constantly keeps an eye on the second component `MESSAGE QUEUE`, it runs in infinite loop
         * 2. Message Queue - where all the tasks are lined up in a sequence
         *
         * Looper sends the tasks to the MAIN thread for execution in a sequential manner
         * */

        Log.i(TAG, "updateCounter: Thread Name: ${Thread.currentThread().name}")

        count++
        binding.counterTv.text = count.toString()
    }
}