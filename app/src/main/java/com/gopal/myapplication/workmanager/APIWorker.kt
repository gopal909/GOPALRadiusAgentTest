package com.gopal.myapplication.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class APIWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}