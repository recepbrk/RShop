package com.example.rshop.data.source

import com.example.rshop.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("/products")
    fun getAllData (): Call<List<ProductModel>>

    @GET("/products/category/jewelery")
    fun getJewelery():Call<List<ProductModel>>

    @GET("/products/category/electronics")
    fun getElectronics():Call<List<ProductModel>>

    @GET("/products/category/men's%20clothing")
    fun getManClothes():Call<List<ProductModel>>

    @GET("products/category/women's%20clothing")
    fun getWomanClothes():Call<List<ProductModel>>

    @GET("products/{id}")
    fun getSingleProduct(@Path("id") id:Int) : Call<ProductModel>
}