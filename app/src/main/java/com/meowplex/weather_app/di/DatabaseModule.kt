package com.meowplex.weather_app.di

import android.content.Context
import androidx.room.Room
import com.meowplex.weather_app.Constants.databaseName
import com.meowplex.weather_app.database.AppDatabase
import com.meowplex.weather_app.database.dao.CitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    fun provideCitiesDao(appDatabase: AppDatabase): CitiesDao {
        return appDatabase.citiesDao()
    }
}