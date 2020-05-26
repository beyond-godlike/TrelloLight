package com.unava.dia.trellolight.data.api.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unava.dia.trellolight.data.Task

@Dao
interface TaskDao {
    @Insert
    fun insertTask(note: Task): Long?

    @Query("SELECT * FROM Task")
    fun fetchAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id =:taskId")
    fun getTask(taskId: Int): LiveData<Task>

    @Query("SELECT * FROM Task WHERE boardId =:boardId")
    fun getTasksForBoard(boardId: Int): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id =:taskId")
    fun getTaskAsync(taskId: Int): Task

    @Update
    fun updateTask(note: Task)

    @Delete
    fun deleteTask(note: Task)
}