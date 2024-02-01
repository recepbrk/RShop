package com.example.rshop.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "rate")
    val rate: Double
):Parcelable