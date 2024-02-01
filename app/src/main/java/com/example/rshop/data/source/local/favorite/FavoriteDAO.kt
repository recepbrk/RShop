package com.example.rshop.data.source.local.favorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rshop.data.model.ProductModel

@Dao
interface FavoriteDAO {
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun addProduct (product: ProductModel):Long

@Delete
suspend fun deleteProduct(product: ProductModel)

@Query("SELECT*FROM favorite_table")
 fun getFavProduct():LiveData<List<ProductModel>>


}