package com.example.schedule.di

import android.app.Application
import com.example.schedule.gropPicker.GroupActivity
import com.example.schedule.schedule.ScheduleActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SharedPreferencesModule::class,
    FireStoreModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: GroupActivity)

    fun inject(target: ScheduleActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}