package com.example.jetbackcompose

import android.app.Application
import com.example.jetbackcompose.database.NewsLocalDataBase

class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        NewsLocalDataBase.init(this)
    }
}