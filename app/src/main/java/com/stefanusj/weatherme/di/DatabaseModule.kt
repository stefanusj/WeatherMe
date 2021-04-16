package com.stefanusj.weatherme.di

import android.app.Application
import androidx.room.Room
import com.stefanusj.weatherme.repository.database.AppDatabase
import com.stefanusj.weatherme.repository.database.LocalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ) = Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideLocalService(database: AppDatabase): LocalService = database.localService()
}