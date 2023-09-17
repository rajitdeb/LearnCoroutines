package com.rajit.learncoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    }
}