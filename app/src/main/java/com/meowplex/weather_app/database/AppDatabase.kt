package com.meowplex.weather_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.meowplex.weather_app.database.converters.Converters
import com.meowplex.weather_app.database.dao.CitiesDao
import com.meowplex.weather_app.database.entities.CityEntity

@Database(entities = [CityEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao

}
