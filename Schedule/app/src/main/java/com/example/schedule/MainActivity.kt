package com.example.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.schedule.app.ScheduleApplication
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import com.example.schedule.firestore.Lesson
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fireStore: FireStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as ScheduleApplication).appComponent.inject(this)

        fireStore.getGroups({list: List<Group> -> Log.d("WTF", "$list")},
            {exception: Exception -> Log.d("WTF", "$exception") })

        fireStore.getAllLessons(
            Group(3,  "TP", 13),
            {list: List<Lesson> -> Log.d("WTF", "$list")},
            {exception: Exception -> Log.d("WTF", "$exception") })
    }
}
