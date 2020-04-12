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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getGroups(){
        _isLoading.value = true
        db.getGroups({list: List<Group> -> processList(list)}, {})
    }

    private fun processList(list: List<Group>){
        _map.value = list.groupBy { it.course }
        _isLoading.value = false
    }

    fun getGroupsForCourse(course: Int):List<Group>{
        val key = _map.value?.keys?.toList()?.get(course)
        key?.let{
            return  _map.value?.get(key) ?: listOf()
        }
        return listOf()
    }
}