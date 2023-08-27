package com.example.mymovieappjc

import android.app.Application
import com.example.mymovieappjc.data.Repository
import com.example.mymovieappjc.network.MovieManager
import com.example.mymovieappjc.network.RemoteDataSource

class MainApp: Application() {
    private val manager by lazy {
        MovieManager(RemoteDataSource.retrofitService)
    }

    val repository by lazy {
        Repository(manager = manager)
    }
}