package com.madesubs.myapplication

import android.app.Application
import com.madesubs.core.di.databaseModule
import com.madesubs.core.di.networkModule
import com.madesubs.core.di.repositoryModule
import com.madesubs.myapplication.di.usecaseModule
import com.madesubs.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}