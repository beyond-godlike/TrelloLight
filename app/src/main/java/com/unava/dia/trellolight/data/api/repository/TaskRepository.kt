package com.unava.dia.trellolight.data.api.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.data.api.AppDatabase


class TaskRepository(context: Context) {

    private val db: AppDatabase = AppDatabase.getAppDataBase(context)!!

    val tasks: LiveData<List<Task>>
        get() = db.taskDao().fetchAllTasks()

    @JvmOverloads
    fun insertTask(
        title: String,
        description: String,
        boardId: Int
    ) {
        insertTask(Task(title, description, boardId))
    }

    fun insertTask(task: Task) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.taskDao().insertTask(task)
                return null
            }
        }.execute()
    }

    fun updateTask(task: Task) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.taskDao().updateTask(task)
                return null
            }
        }.execute()
    }

    fun deleteTask(id: Int) {
        val task = getTaskAsync(id)
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.taskDao().deleteTask(task)
                return null
            }
        }.execute()
    }

    fun deleteTask(note: Task) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                db.taskDao().deleteTask(note)
                return null
            }
        }.execute()
    }

    fun getTask(id: Int): LiveData<Task>? {
        return db.taskDao().getTask(id)
    }

    fun findRepositoriesForTask(boardId: Int): LiveData<List<Task>>? {
        return db.taskDao().getTasksForBoard(boardId)
    }

    private fun getTaskAsync(tId: Int): Task {
        return GetBoardById(db, tId).execute().get()
    }

    private class GetBoardById(var taskDatabase: AppDatabase, var tId: Int) :
        AsyncTask<Void, Void, Task>() {
        override fun doInBackground(vararg boards: Void): Task? {
            return taskDatabase.taskDao().getTaskAsync(tId)
        }
    }
}
