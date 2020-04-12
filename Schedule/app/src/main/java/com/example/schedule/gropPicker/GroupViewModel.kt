package com.example.schedule.gropPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.app.SharedPreferencesWrapper
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import com.example.schedule.firestore.Lesson
import javax.inject.Inject

class GroupViewModel @Inject constructor(
    private val db: FireStore, private val spWrapper: SharedPreferencesWrapper): ViewModel(){


    private val _map = MutableLiveData<Map<Int, List<Group>>>()
    val map: LiveData<Map<Int, List<Group>>>
        get() = _map

    fun getGroups(){
        db.getGroups({list: List<Group> -> processList(list)}, {})
    }

    private fun processList(list: List<Group>){
           _map.value = list.groupBy { it.course }
    }

    fun getGroupsForCourse(course: Int):List<Group>{
        val key = _map.value?.keys?.toList()?.get(course)
        key?.let{
            return  _map.value?.get(key) ?: listOf()
        }
        return listOf()
    }
}