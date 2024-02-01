package com.example.rshop.data.repository

import androidx.lifecycle.LiveData
import com.example.rshop.data.model.ProductModel
import com.example.rshop.data.source.local.favorite.FavoriteDAO
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import com.example.rshop.data.source.remote.NetworkService
import com.example.rshop.util.resource.Resource
import java.lang.Error
import java.lang.Exception
import javax.inject.Inject

class ProductRepository @Inject constructor(private val networkService:NetworkService,private val db:FavoriteDAO) {


    suspend fun getAllProduct() = try {
        Resource.Success(networkService.getAllData())
    } catch (exception: Exception) {
        Resource.Error(exception.message.orEmpty())
    }

    suspend fun getFiveProduct() = try {
        Resource.Success(networkService.getFiveProduct())
    } catch (exception: Exception) {
        Resource.Error(exception.message.orEmpty())

    }

    suspend fun getDifferentCategory(category: String) = try {
        Resource.Success(networkService.getDifferentCategory(category))
    } catch (exception: Exception) {
        Resource.Error(exception.message.orEmpty())
    }

    suspend fun getSingleProduct(id: Int) = try {
        Resource.Success(networkService.getSingleProduct(id))
    } catch (exception: Exception) {
        Resource.Error(exception.message.orEmpty())
    }


    suspend fun addFavoriteProduct(product:ProductModel):Long{
        return db.addProduct(product)
    }

    suspend fun deleteFavoriteProduct(product: ProductModel){
        return db.deleteProduct(product)
    }

    fun getFavoriteList():LiveData<List<ProductModel>>{
        return db.getFavProduct()
    }
}