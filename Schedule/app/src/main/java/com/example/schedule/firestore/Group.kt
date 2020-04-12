package com.example.schedule.firestore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Group(
    val course: Int = 0,
    val department: String = "" ,
    val group: Int = 0 ): Parcelable