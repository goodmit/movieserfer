package com.goodmit.movieserfer.startup

import android.app.Application
import com.goodmit.movieserfer.di.appModule
import com.goodmit.movieserfer.di.dataModule
import com.goodmit.movieserfer.di.domainModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Startup : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this@Startup)

        startKoin {
            androidContext(this@Startup)
            modules(listOf(domainModule, dataModule, appModule))
        }
    }
}