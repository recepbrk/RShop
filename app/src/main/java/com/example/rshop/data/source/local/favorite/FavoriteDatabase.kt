package com.example.rshop.data.source.local.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rshop.data.model.ProductModel

@Database(entities = [ProductModel::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class FavoriteDatabase:RoomDatabase() {

abstract fun getFavoriteFromDao():FavoriteDAO
}