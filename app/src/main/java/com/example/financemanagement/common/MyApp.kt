package com.example.financemanagement.common

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}