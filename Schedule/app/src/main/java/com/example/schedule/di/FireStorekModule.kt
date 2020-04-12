package com.example.schedule.di

import com.example.schedule.firestore.FireStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireStorekModule {

    @Provides
    @Singleton
    fun provideFireStore() = FireStore()


}