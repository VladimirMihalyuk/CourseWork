package com.example.schedule.app

import android.app.Application
import android.content.SharedPreferences
import com.example.schedule.firestore.Group


private val SPNAME = "MyPref"
private val GROUP_KEY = "LOGIN_KEY"
private val DEPARTMENT_KEY = "DEPARTMENT_KEY"
private val COURSE_KEY = "COURSE_KEY"

class SharedPreferencesWrapper(private val application: Application){

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = application.getSharedPreferences(SPNAME, 0)
    }

    fun setGroup(group: Group){
        val editor = sharedPreferences.edit()
        editor.putInt(GROUP_KEY, group.group)
        editor.putString(DEPARTMENT_KEY, group.department)
        editor.putInt(COURSE_KEY, group.course)
        editor.apply()
    }

    fun getGroup(): Group?{
        val group = sharedPreferences.getInt(GROUP_KEY, 0)
        val department = sharedPreferences.getString(DEPARTMENT_KEY, null)
        val course = sharedPreferences.getInt(COURSE_KEY, 0)

        if(department != null){
            return Group(course, department, group)
        } else {
            return null
        }
    }
}