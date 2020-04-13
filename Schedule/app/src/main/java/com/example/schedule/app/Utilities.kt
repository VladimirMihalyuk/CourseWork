package com.example.schedule.app

import com.example.schedule.firestore.Group
import java.text.SimpleDateFormat
import java.util.*

fun Group.getGroupString():String{
    var string = "группа ${this.group}"
    if(this.department != ""){
        string += "-${this.department}"
    }
    return string
}

fun Long.getDate(): String {
    val formatter = SimpleDateFormat("EEEE dd.MM.yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    return formatter.format(calendar.time)
}