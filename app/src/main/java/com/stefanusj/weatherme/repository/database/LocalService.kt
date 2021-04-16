package com.stefanusj.weatherme.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stefanusj.weatherme.repository.model.OpenWeatherDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalService {

    @Query("SELECT * FROM open_weather ORDER BY id DESC")
    fun getOpenWeathers(): Flow<List<OpenWeatherDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOpenWeather(openWeather: OpenWeatherDatabase): Long
}