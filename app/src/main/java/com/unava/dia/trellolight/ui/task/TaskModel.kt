package com.unava.dia.trellolight.ui.task


import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.data.TaskUseCase
import javax.inject.Inject

class TaskModel @Inject constructor( private var useCase: TaskUseCase){
    fun findTask(id: Int) : LiveData<Task> {
        return this.useCase.getTask(id)
    }

    fun deleteTask(id: Int) {
        this.useCase.deleteTask(id)
    }

    fun updateTask(task: Task) {
        this.useCase.updateTask(task)
    }

    fun insertTask(task: Task) {
        this.useCase.insertTask(task)
    }
}