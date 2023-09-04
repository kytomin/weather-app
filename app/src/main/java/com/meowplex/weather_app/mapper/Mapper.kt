package com.meowplex.weather_app.mapper

interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
}