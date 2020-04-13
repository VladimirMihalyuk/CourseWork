package com.example.schedule.schedule

import androidx.lifecycle.ViewModel
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import javax.inject.Inject

class ScheduleViewModel @Inject constructor( private val db: FireStore): ViewModel() {

    private lateinit var group: Group

    fun setGroup(groupG: Group){
        group = groupG
    }

}