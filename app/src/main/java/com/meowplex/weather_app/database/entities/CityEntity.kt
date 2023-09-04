package com.meowplex.weather_app.database.entities

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meowplex.weather_app.Constants.tableName

@Entity(tableName)
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: Location,
    @ColumnInfo(name = "is_primary") val isPrimary: Boolean = false,
)