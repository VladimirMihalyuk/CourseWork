package com.example.schedule.app

import com.example.schedule.firestore.Group

fun Group.getGroupString():String{
    var string = "группа ${this.group}"
    if(this.department != ""){
        string += "-${this.department}"
    }
    return string
}