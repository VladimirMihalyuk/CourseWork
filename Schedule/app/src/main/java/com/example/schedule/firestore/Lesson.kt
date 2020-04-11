package com.example.schedule.firestore

data class Lesson(
    val audience: String ="",
    val dateEnd: Long = 0L,
    val dateStart: Long = 0L,
    val dayOfWeek: String = "",
    val department: String = "",
    val subject: String = "",
    val teacher: String = "",
    val timeEnd: String = "",
    val timeStart: String = ""){}