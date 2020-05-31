package com.unava.dia.trellolight.data

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import javax.inject.Inject

class BoardsUseCase @Inject constructor(private var boardRepository: BoardRepository) {
    fun findAllBoardsAsync(): LiveData<List<Board>>? {
        return boardRepository.getBoards()
    }
}