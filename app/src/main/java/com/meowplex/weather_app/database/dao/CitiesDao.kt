package com.meowplex.weather_app.database.dao

import androidx.room.*
import com.meowplex.weather_app.Constants.tableName
import com.meowplex.weather_app.database.entities.CityEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CitiesDao {
    @Query("SELECT * FROM $tableName")
    fun getAll(): Single<List<CityEntity>>

    @Update
    fun update(city: CityEntity): Completable

    @Insert
    fun insert(city: CityEntity): Completable

    @Insert
    fun insertMany(vararg cities: CityEntity): Completable

    @Delete
    fun delete(city: CityEntity): Completable

    @Delete
    fun deleteMany(vararg cities: CityEntity): Completable

    @Query("DELETE FROM $tableName WHERE uid = :id")
    fun deleteById(id: Long): Completable

    @Query("DELETE FROM $tableName")
    fun deleteAll(): Completable
}