package com.unava.dia.trellolight.di.useCases

import android.content.Context
import com.unava.dia.trellolight.data.BoardsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Singleton
    @Provides
    fun provideBoardsUseCase(context: Context): BoardsUseCase {
        return BoardsUseCase(context)
    }
}