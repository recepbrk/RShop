package com.example.rshop.data.repository

import androidx.lifecycle.LiveData
import com.example.rshop.data.source.local.basket.BasketDAO
import com.example.rshop.data.source.local.basket.BasketEntity
import com.example.rshop.data.source.local.favorite.FavoriteDAO
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import com.example.rshop.data.source.remote.NetworkService
import com.example.rshop.util.resource.Resource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val networkService: NetworkService,
    private val favoritedb: FavoriteDAO,
    private val basketdb: BasketDAO
) {


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
    suspend fun getSingleBasketProduct(id: Int) = try {
        Resource.Success(networkService.getSingleBasketProduct(id))
    } catch (exception: Exception) {
        Resource.Error(exception.message.orEmpty())
    }


    suspend fun addFavoriteProduct(product: FavoriteEntity): Long {
        return favoritedb.addProduct(product)
    }

    suspend fun deleteFavoriteProduct(product: FavoriteEntity) {
        return favoritedb.deleteProduct(product)
    }

    fun getFavoriteList(): LiveData<List<FavoriteEntity>> {
        return favoritedb.getFavProduct()
    }

    suspend fun addBasketProduct(product: BasketEntity): Long {
        return basketdb.addProduct(product)
    }

    suspend fun deleteBasketProduct(product: BasketEntity) {
        return basketdb.deleteProduct(product)
    }

    fun getBasketList(): LiveData<List<BasketEntity>> {
        return basketdb.getFavProduct()
    }

    suspend fun increaseBasketProductQuantity(product: BasketEntity) {
        basketdb.increaseBasketProductQuantity(product.id)
    }

    suspend fun decreaseBasketProductQuantity(product: BasketEntity) {
        val currentQuantity = basketdb.getBasketProductQuantity(product.id.toLong())
        if (currentQuantity > 1) {
            basketdb.decreaseBasketProductQuantity(product.id)
        } else {

            deleteBasketProduct(product)
        }
    }
}