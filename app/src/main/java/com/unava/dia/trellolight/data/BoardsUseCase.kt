package com.unava.dia.trellolight.data

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import javax.inject.Inject

class BoardsUseCase @Inject constructor(private var boardRepository: BoardRepository) {
    fun findAllBoardsAsync(): LiveData<List<Board>>? {
        return boardRepository.getBoards()
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

    fun insertBoard(board: Board) : Long? {
        return boardRepository.insertBoard(board)
    }
}