package com.example.rshop.data.source.local.basket

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rshop.data.source.local.favorite.TypeConvertor

@Database(entities = [BasketEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class BasketDatabase : RoomDatabase() {

    abstract fun getBasketFromDao(): BasketDAO
}