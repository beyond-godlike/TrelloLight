package com.unava.dia.trellolight.di.subModules

import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.ui.task.TaskViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class TaskViewModule {
    @Binds
    abstract fun bindTaskViewModel(viewModel: TaskViewModel) : ViewModel
}