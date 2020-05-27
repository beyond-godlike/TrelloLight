package com.unava.dia.trellolight.ui.board

import android.content.Context
import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.data.Board
import javax.inject.Inject

class BoardViewModel @Inject constructor(
    private val context: Context,
    private val model: BoardModel
) : ViewModel(){

    fun getTasks() = model.findAllTasks()

    fun getBoard(id: Int) = model.findBoard(id)

    fun deleteBoard(id: Int) {
        model.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        model.updateBoard(board)
    }

    fun insertBoard(text: String) {
        model.insertBoard(text)
    }
    fun findReposForTask (boardId: Int) = model.findReposForTask(boardId)

}