package com.example.assignment

import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

class Forum(
    var id: Int,
    var mem_id: Int,
    var title: String,
    var description: String,
    var uploadDate: String,
    var timeZone: String)
{
    constructor(): this(0,"", "")

    constructor(
        mem_id: Int,
        title: String,
        description: String): this(0, mem_id, title, description, "", ""){
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        this.uploadDate = LocalDateTime.now().format(formatter)
        this.timeZone = TimeZone.getDefault().id
        Log.d("Forum", this.uploadDate)
    }

}

