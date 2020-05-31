package com.unava.dia.trellolight.data

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.TaskRepository
import javax.inject.Inject

class TaskUseCase @Inject constructor(private var taskRepository: TaskRepository) {

    fun getTask(id: Int): LiveData<Task> {
        return taskRepository.getTask(id)
    }

    fun deleteTask(id: Int) {
        taskRepository.deleteTask(id)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    fun insertTask(task: Task) {
        taskRepository.insertTask(task)
    }

}