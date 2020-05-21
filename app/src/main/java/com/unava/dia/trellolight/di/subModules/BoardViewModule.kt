package com.unava.dia.trellolight.di.subModules

import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.ui.board.BoardViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class BoardViewModule {
    @Binds
    abstract fun bindBoardViewModel(viewModel: BoardViewModel) : ViewModel
}