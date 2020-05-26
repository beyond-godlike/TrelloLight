package com.unava.dia.trellolight.ui.board

import android.content.Context
import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import javax.inject.Inject

class BoardViewModel @Inject constructor(
    private val context: Context,
    private val model: BoardModel
) : ViewModel(){
    var boardRepository: BoardRepository? = BoardRepository(context)
    fun deleteBoard(id: Int) {
        boardRepository!!.deleteBoard(id)
    }

    fun updateBoard(board: Board) {
        boardRepository!!.updateBoard(board)
    }

    fun insertBoard(text: String) {
        boardRepository!!.insertBoard(Board(text))
    }
}