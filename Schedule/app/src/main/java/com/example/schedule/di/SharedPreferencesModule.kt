package com.example.schedule.di

import android.app.Application
import com.example.schedule.app.SharedPreferencesWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SharedPreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreferencesWrapper(application: Application)
            = SharedPreferencesWrapper(application)
}