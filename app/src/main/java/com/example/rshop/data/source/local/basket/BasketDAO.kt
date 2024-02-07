package com.example.rshop.data.source.local.basket

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BasketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: BasketEntity): Long

    @Delete
    suspend fun deleteProduct(product: BasketEntity)

    @Query("SELECT*FROM basket_table")
    fun getFavProduct(): LiveData<List<BasketEntity>>

    @Query("UPDATE basket_table SET quantity = quantity + 1 WHERE id = :productId")
    suspend fun increaseBasketProductQuantity(productId: Int)

    @Query("UPDATE basket_table SET quantity = quantity - 1 WHERE id = :productId")
    suspend fun decreaseBasketProductQuantity(productId: Int)

    @Query("SELECT quantity FROM basket_table WHERE id = :productId")
    suspend fun getBasketProductQuantity(productId: Long): Int
}
