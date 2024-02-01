package com.example.rshop.data.source.local.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rshop.data.model.Rating

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "rating")
    val rating: com.example.rshop.data.source.local.favorite.Rating,
    @ColumnInfo(name ="title")
    val title: String

)
data class Rating(
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "rate")
    val rate: Double
)