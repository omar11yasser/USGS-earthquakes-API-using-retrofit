package com.orange.internship.usgsearthquakesapi_usingretrofit

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                networkModule,
                presenterModule
            ))
            androidContext(this@MyApplication)
        }
    }
}