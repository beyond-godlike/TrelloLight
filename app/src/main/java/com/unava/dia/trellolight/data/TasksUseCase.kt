package com.unava.dia.trellolight.data

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import com.unava.dia.trellolight.data.api.repository.TaskRepository
import javax.inject.Inject

class TasksUseCase @Inject constructor(
    private var boardRepository: BoardRepository,
    private var taskRepository: TaskRepository
) {

    fun findAllTasksAsync(): LiveData<List<Task>>? {
        return taskRepository.getTasks()
    }

    fun getBoard(id: Int): LiveData<Board> {
        return boardRepository.getBoard(id)
    }

    fun deleteBoard(id: Int) {
        boardRepository.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        boardRepository.updateBoard(board)
    }

    fun insertBoard(board: Board) {
        boardRepository.insertBoard(board)
    }

    fun findRepositoriesForTask(boardId: Int): LiveData<List<Task>>? {
        return taskRepository.findRepositoriesForTask(boardId)
    }
}