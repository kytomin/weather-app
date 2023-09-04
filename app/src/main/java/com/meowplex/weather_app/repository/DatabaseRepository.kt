package com.meowplex.weather_app.repository

import com.meowplex.weather_app.database.dao.CitiesDao
import com.meowplex.weather_app.database.entities.CityEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(private val citiesDao: CitiesDao) {

    fun getCities() = citiesDao.getAll()
    fun addCity(city: CityEntity) = citiesDao.insert(city)
    fun deleteCity(city: CityEntity) = citiesDao.delete(city)
    fun deleteCityById(id: Long) = citiesDao.deleteById(id)
    fun deleteCities(cities: List<CityEntity>) = citiesDao.deleteMany(*cities.toTypedArray())
    fun clear() = citiesDao.deleteAll()
    fun updateCity(city: CityEntity) = citiesDao.update(city)


}