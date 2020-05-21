package com.unava.dia.trellolight.di.subModules

import androidx.lifecycle.ViewModel
import com.unava.dia.trellolight.ui.main.MainViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class MainViewModule {
    @Binds
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel
}