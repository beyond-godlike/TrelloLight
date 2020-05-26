package com.unava.dia.trellolight.ui.main

import androidx.lifecycle.LiveData
import com.unava.dia.trellolight.data.Board
import com.unava.dia.trellolight.data.BoardsUseCase
import javax.inject.Inject

class MainModel @Inject constructor(private var useCase: BoardsUseCase){
    fun findAllBoards(): LiveData<List<Board>>? {
        return this.useCase.findAllBoardsAsync()
    }
}