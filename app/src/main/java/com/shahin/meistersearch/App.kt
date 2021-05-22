package com.shahin.meistersearch

import android.app.Application
import com.shahin.meistersearch.di.repositoryModule
import com.shahin.meistersearch.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                uiModule,
                repositoryModule
            )
        }
    }

}