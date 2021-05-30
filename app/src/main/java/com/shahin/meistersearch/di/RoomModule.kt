package com.shahin.meistersearch.di

import android.app.Application
import androidx.room.Room
import com.shahin.meistersearch.data.local.sources.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Module providing a single instance of [AppDatabase]
 */
val roomModule = module {
    single { provideDatabase(androidApplication()) }
}

private fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "meister_db")
        .fallbackToDestructiveMigration()
        .build()
}