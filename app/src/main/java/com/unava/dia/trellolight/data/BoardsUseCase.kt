package com.unava.dia.trellolight.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository

class BoardsUseCase(private val context: Context) {
    // TODO inject board repository
    private val boardRepository: BoardRepository? = BoardRepository(context)
    fun findAllBoardsAsync(): LiveData<List<Board>>? {
        return boardRepository?.getBoards()
    }
}