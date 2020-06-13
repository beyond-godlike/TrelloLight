package com.unava.dia.trellolight.data

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import com.unava.dia.trellolight.data.api.repository.TaskRepository
import javax.inject.Inject

class TasksUseCase @Inject constructor(
    private var boardRepository: BoardRepository,
    private var taskRepository: TaskRepository
) {
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

    fun findAllTasksAsync(): LiveData<List<Task>>? {
        return taskRepository.getTasks()
    }

    fun findRepositoriesForTask(boardId: Int): LiveData<List<Task>>? {
        return taskRepository.findRepositoriesForTask(boardId)
    }
}