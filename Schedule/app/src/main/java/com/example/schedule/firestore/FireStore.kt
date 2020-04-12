package com.example.schedule.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception
import javax.security.auth.callback.Callback

class FireStore(){
    private val db = FirebaseFirestore.getInstance()

    fun getGroups(successCallback: (list: List<Group>) -> Unit,
                  failureCallback: (exception: Exception) -> Unit){

        db.collection("/BSU/FAMCS/courses")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val task = querySnapshot.toObjects(Group::class.java)
                successCallback.invoke(task)
            }
            .addOnFailureListener { exception ->
                failureCallback.invoke(exception)
            }
    }

    fun getAllLessons(group: Group, successCallback: (list: List<Lesson>) -> Unit,
                      failureCallback: (exception: Exception) -> Unit){

        db.collection("/BSU/FAMCS/timetable/course/course${group.course}-${group.group}")
            .whereEqualTo("department", group.department)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val task = querySnapshot.toObjects(Lesson::class.java)
                successCallback.invoke(task)
            }
            .addOnFailureListener { exception ->
                failureCallback.invoke(exception)
            }
    }
}