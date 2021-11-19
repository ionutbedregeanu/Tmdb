package com.tmdb.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TmdbTypeConverters {

    @TypeConverter
    fun stringToInt(json: String?): List<Int>? {
        val type: Type = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(json, type)
    }
    @TypeConverter
    fun intToString(items: List<Int>?): String? {
        val type = object : TypeToken<List<Int>?>() {}.type
        return Gson().toJson(items, type)
    }
}
