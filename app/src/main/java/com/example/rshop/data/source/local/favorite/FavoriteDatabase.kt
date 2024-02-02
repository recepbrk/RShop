package com.example.rshop.data.source.local.favorite

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class FavoriteDatabase:RoomDatabase() {

abstract fun getFavoriteFromDao():FavoriteDAO
}