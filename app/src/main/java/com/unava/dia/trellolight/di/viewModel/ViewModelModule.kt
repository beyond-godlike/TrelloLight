package com.unava.dia.trellolight.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unava.dia.trellolight.ui.board.BoardViewModel
import com.unava.dia.trellolight.ui.main.MainViewModel
import com.unava.dia.trellolight.ui.task.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    // factory is singleton
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    internal abstract fun bindMmrCheckerViewModel(viewModel: TaskViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BoardViewModel::class)
    internal abstract fun bindMatchesHistoryViewModel(viewModel: BoardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    //Add more ViewModels here
}