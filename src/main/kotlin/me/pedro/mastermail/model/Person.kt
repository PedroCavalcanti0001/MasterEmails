package me.pedro.mastermail.model

import java.time.LocalDateTime
import java.util.TimeZone
import java.time.Instant

data class Person(var id:Int, var name:String, var email:String,var date:Long)

fun Person.dateLocalAsDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(
        Instant.ofEpochSecond(date),
        TimeZone.getDefault().toZoneId()
    )
}