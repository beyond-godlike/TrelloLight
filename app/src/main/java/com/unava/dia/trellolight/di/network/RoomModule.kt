package com.unava.dia.trellolight.di.network

import android.app.Application
import android.content.Context
import com.unava.dia.trellolight.data.api.AppDatabase
import com.unava.dia.trellolight.data.api.dao.BoardDao
import com.unava.dia.trellolight.data.api.dao.TaskDao
import com.unava.dia.trellolight.data.api.repository.BoardRepository
import com.unava.dia.trellolight.data.api.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getAppDataBase(application.applicationContext)!!
    }

    @Provides
    @Singleton
    fun provideBoardDao(appDatabase: AppDatabase): BoardDao {
        return appDatabase.boardDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideBoardRepository(context: Context): BoardRepository {
        return BoardRepository(context)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(context: Context): TaskRepository {
        return TaskRepository(context)
    }
}