package com.justeattakeawayexercise

import android.app.Application
import com.justeattakeawayexercise.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
//            modules(listOf(networkModule, dataModule, viewModelModule, resourceModule, roomModule))
            modules(listOf(modules, viewModelModule))
        }
    }
}