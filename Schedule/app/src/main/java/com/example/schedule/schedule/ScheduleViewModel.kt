package com.example.schedule.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.app.getDayOfWeek
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import com.example.schedule.firestore.Lesson
import javax.inject.Inject

class ScheduleViewModel @Inject constructor( private val db: FireStore): ViewModel() {

    private lateinit var group: Group

    fun setGroup(groupG: Group){
        group = groupG
    }

    fun getSchedule(){
        db.getAllLessons(group, {list: List<Lesson> -> processList(list)}, {})
    }

    private val _map = MutableLiveData<Map<String, List<Lesson>>>()

    private fun processList(list: List<Lesson>){
        _map.postValue(list.groupBy{it.dayOfWeek})
    }

    fun getLessons(time: Long){
        val date = (time / 86400000) * 86400000
        Log.d("WTF", "${date.getDayOfWeek()}")
    }
}