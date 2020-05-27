package com.unava.dia.trellolight.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import com.unava.dia.trellolight.data.api.repository.TaskRepository

class TaskUseCase(private val context: Context) {
    // TODO inject board repository & task repository
    private val boardRepository: BoardRepository? = BoardRepository(context)
    private val taskRepository: TaskRepository? = TaskRepository(context)

    fun findAllTasksAsync(): LiveData<List<Task>>?{
        return  taskRepository!!.getTasks()
    }

    fun getBoard(id: Int): LiveData<Board> {
        return boardRepository!!.getBoard(id)
    }

    fun deleteBoard(id: Int) {
        boardRepository!!.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        boardRepository!!.updateBoard(board)
    }

    fun insertBoard(board: Board) {
        boardRepository!!.insertBoard(board)
    }

    fun findRepositoriesForTask(boardId: Int): LiveData<List<Task>>? {
        return taskRepository!!.findRepositoriesForTask(boardId)
    }
}