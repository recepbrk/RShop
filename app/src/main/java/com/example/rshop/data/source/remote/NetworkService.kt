package com.example.rshop.data.source.remote

import com.example.rshop.data.model.ProductModel
import com.example.rshop.data.source.local.basket.BasketEntity
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import com.example.rshop.util.constants.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET(Constants.ALL_PRODUCT)
    suspend fun getAllData(): List<ProductModel>

    @GET(Constants.FIVE_PRODUCT)
    suspend fun getFiveProduct(): List<ProductModel>

    @GET(Constants.DIFFERENT_CATEGORY)
    suspend fun getDifferentCategory(
        @Path("category") category: String
    ): List<ProductModel>

    @GET(Constants.DETAILS_PRODUCT)
    suspend fun getSingleProduct(@Path("id") id: Int): FavoriteEntity
    @GET(Constants.DETAILS_PRODUCT)
    suspend fun getSingleBasketProduct(@Path("id") id: Int): BasketEntity
}