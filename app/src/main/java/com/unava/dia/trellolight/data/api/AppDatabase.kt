package com.unava.dia.trellolight.data.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unava.dia.trellolight.data.api.dao.BoardDao
import com.unava.dia.trellolight.data.api.dao.TaskDao
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.utils.TaskListConverter

@Database(entities = [Board::class, Task::class], version = 1, exportSchema = false)
@TypeConverters(TaskListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    abstract fun taskDao(): TaskDao

    // copy-paste from stackoverflow / not mine if wrong (:
    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}