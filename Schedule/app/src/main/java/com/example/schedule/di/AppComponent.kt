package com.example.schedule.di

import android.app.Application
import com.example.schedule.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SharedPreferencesModule::class,
    FireStorekModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: MainActivity)
//
//    fun inject(target: AllRunsFragment)
//
//    fun inject(target: AddNewRunOrUpdateFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}