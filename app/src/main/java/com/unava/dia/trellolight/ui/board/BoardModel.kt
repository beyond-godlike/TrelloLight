package com.unava.dia.trellolight.ui.board

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.BoardsUseCase
import com.unava.dia.trellolight.data.Task
import com.unava.dia.trellolight.data.TasksUseCase
import javax.inject.Inject

class BoardModel @Inject constructor(private var tasksUseCase: TasksUseCase, private var boardsUseCase: BoardsUseCase){

    fun findAllTasks(): LiveData<List<Task>>? {
        return this.tasksUseCase.findAllTasksAsync()
    }

    fun findBoard(id: Int) : LiveData<Board> {
        return this.boardsUseCase.getBoard(id)
    }

    fun deleteBoard(id: Int) {
        this.boardsUseCase.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        this.boardsUseCase.updateBoard(board)
    }

    fun insertBoard(text: String) : Long? {
        return this.boardsUseCase.insertBoard(Board(text))
    }

    fun insertBoard(board: Board) : Long? {
        return this.boardsUseCase.insertBoard(board)
    }

    fun findReposForTask(boardId: Int) : LiveData<List<Task>>? {
        return this.tasksUseCase.findRepositoriesForTask(boardId)
    }
}