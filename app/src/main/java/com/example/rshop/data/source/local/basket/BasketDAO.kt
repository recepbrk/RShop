package com.example.rshop.data.source.local.basket

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rshop.data.source.local.favorite.FavoriteEntity


@Dao
interface BasketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: BasketEntity): Long

    @Delete
    suspend fun deleteProduct(product: BasketEntity)

    @Query("SELECT*FROM basket_table")
    fun getFavProduct(): LiveData<List<BasketEntity>>


}
