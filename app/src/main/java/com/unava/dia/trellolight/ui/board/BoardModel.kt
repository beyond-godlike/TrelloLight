package com.unava.dia.trellolight.ui.board

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.data.TasksUseCase
import javax.inject.Inject

class BoardModel @Inject constructor(private var useCase: TasksUseCase){

    fun findAllTasks(): LiveData<List<Task>>? {
        return this.useCase.findAllTasksAsync()
    }

    fun findBoard(id: Int) : LiveData<Board> {
        return this.useCase.getBoard(id)
    }

    fun deleteBoard(id: Int) {
        this.useCase.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        this.useCase.updateBoard(board)
    }

    fun insertBoard(text: String) {
        this.useCase.insertBoard(Board(text))
    }

    fun findReposForTask(boardId: Int) : LiveData<List<Task>>? {
        return this.useCase.findRepositoriesForTask(boardId)
    }
}