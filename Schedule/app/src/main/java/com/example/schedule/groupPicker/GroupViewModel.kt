package com.example.schedule.groupPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.schedule.app.SharedPreferencesWrapper
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import java.lang.NumberFormatException
import javax.inject.Inject

class GroupViewModel @Inject constructor(
    private val db: FireStore, private val spWrapper: SharedPreferencesWrapper): ViewModel(){

    private val _group = MutableLiveData<Group>()
    val group: LiveData<Group>
        get() = _group

    fun resetGroup(){
        _group.value = null
    }

    init {
        _group.value = spWrapper.getGroup()
    }

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

    fun getGroupsForCourse(course: Int):List<Group>{
        val key = _map.value?.keys?.toList()?.get(course)
        key?.let{
            return  _map.value?.get(key) ?: listOf()
        }
        return listOf()
    }

    fun pickGroup(course: String, group: String){
        val courseString = course.replace(" курс", "")
        val values = group.split("-")
        var department = ""
        values.getOrNull(1)?.let{
            department = it
        }
        val groupString = values[0].replace("группа ", "")
        try {
            val groupObject =
                Group(courseString.toInt(),
                department,
                groupString.toInt())
            _group.postValue(groupObject)
            spWrapper.setGroup(groupObject)
        }catch (e: NumberFormatException){}
    }


    private fun processList(list: List<Group>){
        _map.value = list.groupBy { it.course }
        _isLoading.value = false
    }


}