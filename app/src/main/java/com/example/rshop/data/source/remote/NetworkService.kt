package com.example.rshop.data.source.remote

import com.example.rshop.data.model.ProductModel
import com.example.rshop.util.constants.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET(Constants.ALL_PRODUCT)
    suspend fun getAllData(): List<ProductModel>

    @GET("products?limit=5")
    suspend fun getFiveProduct(): List<ProductModel>

    @GET(Constants.DIFFERENT_CATEGORY)
    suspend fun getDifferentCategory(
        @Path("category") category: String
    ): List<ProductModel>

    @GET("products/{id}")
     suspend fun getSingleProduct(@Path("id") id: Int):ProductModel
}