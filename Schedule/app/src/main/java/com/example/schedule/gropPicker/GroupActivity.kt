package com.example.schedule.gropPicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.schedule.R
import com.example.schedule.app.ScheduleApplication
import com.example.schedule.firestore.FireStore
import com.example.schedule.firestore.Group
import com.example.schedule.firestore.Lesson
import java.lang.Exception
import javax.inject.Inject

class GroupActivity : AppCompatActivity() {

    @Inject
    lateinit var fireStore: FireStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        (application as ScheduleApplication).appComponent.inject(this)

        fireStore.getGroups({list: List<Group> -> Log.d("WTF", "$list")},
            {exception: Exception -> Log.d("WTF", "$exception") })

        fireStore.getAllLessons(
            Group(3,  "TP", 13),
            {list: List<Lesson> -> Log.d("WTF", "$list")},
            {exception: Exception -> Log.d("WTF", "$exception") })
    }
}
