package com.unava.dia.trellolight.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.unava.dia.trellolight.data.Task
import java.util.*

object TaskListConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String?) : List<Task>{
        return when (value) {
            null -> Collections.emptyList()
            else -> {
                val gson = Gson()
                val type = object : TypeToken<List<Task>>() {}.type
                gson.fromJson(value, type)
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun toTaskList(value: List<Task>) : String {
        val gson = Gson()
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.toJson(value, type)
    }
}