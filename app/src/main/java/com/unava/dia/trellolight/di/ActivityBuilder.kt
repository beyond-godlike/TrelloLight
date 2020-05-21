package com.unava.dia.trellolight.di

import com.unava.dia.trellolight.di.subModules.BoardViewModule
import com.unava.dia.trellolight.di.subModules.MainViewModule
import com.unava.dia.trellolight.di.subModules.TaskViewModule
import com.unava.dia.trellolight.ui.board.BoardActivity
import com.unava.dia.trellolight.ui.main.MainActivity
import com.unava.dia.trellolight.ui.task.TaskActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        TaskViewModule::class
    ])
    internal abstract fun bindTaskActivity(): TaskActivity

    @ContributesAndroidInjector(modules = [
        BoardViewModule::class
    ])
    internal abstract fun bindBoardActivity(): BoardActivity


    @ContributesAndroidInjector(modules = [
        MainViewModule::class
    ])
    internal abstract fun bindMainActivity(): MainActivity
}