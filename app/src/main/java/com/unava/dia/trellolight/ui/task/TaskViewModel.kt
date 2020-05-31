package com.unava.dia.trellolight.ui.task

import android.content.Context
import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.data.Task
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val context: Context,
    private val model: TaskModel
) : ViewModel(){

    fun getTask(id: Int) = model.findTask(id)

    fun deleteTask(id: Int) {
        model.deleteTask(id)
    }

    fun updateTask(task: Task) {
        model.updateTask(task)
    }

    fun insertTask(task: Task) {
        model.insertTask(task)
    }

}