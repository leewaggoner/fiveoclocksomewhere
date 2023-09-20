package com.wreckingballsoftware.fiveoclocksomewhere

import android.app.Application
import com.wreckingballsoftware.fiveoclocksomewhere.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FiveApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@FiveApplication)
            modules(modules = appModule)
        }
    }
}