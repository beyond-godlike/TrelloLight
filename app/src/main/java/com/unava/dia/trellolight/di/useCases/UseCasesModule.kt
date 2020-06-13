package com.unava.dia.trellolight.di.useCases

import com.unava.dia.trellolight.data.BoardsUseCase
import com.unava.dia.trellolight.data.TasksUseCase
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import com.unava.dia.trellolight.data.api.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Singleton
    @Provides
    fun provideBoardsUseCase(boardRepository: BoardRepository): BoardsUseCase {
        return BoardsUseCase(boardRepository)
    }

    @Singleton
    @Provides
    fun provideTasksUseCase(boardRepository: BoardRepository, taskRepository: TaskRepository): TasksUseCase {
        return TasksUseCase(boardRepository, taskRepository)
    }
}