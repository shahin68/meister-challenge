package com.shahin.meistersearch

import androidx.multidex.MultiDexApplication
import com.shahin.meistersearch.di.networkModule
import com.shahin.meistersearch.di.repositoryModule
import com.shahin.meistersearch.di.roomModule
import com.shahin.meistersearch.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                uiModule,
                repositoryModule,
                networkModule,
                roomModule
            )
        }
    }

}