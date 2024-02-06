package com.example.rshop.data.model

import android.os.Parcelable
import com.example.rshop.data.source.local.favorite.Rating
import kotlinx.parcelize.Parcelize

@Parcelize

data class ProductModel(
    val id: Int = 0,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: com.example.rshop.data.model.Rating,
    val title: String
):Parcelable

