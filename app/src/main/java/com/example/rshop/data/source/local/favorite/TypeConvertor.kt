package com.example.rshop.data.source.local.favorite

import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConvertor {

    @TypeConverter
    fun fromRating(rating: Rating): String? {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(name: String): Rating? {
        return Gson().fromJson(name, Rating::class.java)
    }
}