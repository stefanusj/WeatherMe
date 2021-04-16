package com.stefanusj.weatherme.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stefanusj.weatherme.repository.model.OpenWeatherDatabase

@Database(entities = [OpenWeatherDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localService(): LocalService

    companion object {
        const val DATABASE_NAME = "weather_me.db"
    }
}