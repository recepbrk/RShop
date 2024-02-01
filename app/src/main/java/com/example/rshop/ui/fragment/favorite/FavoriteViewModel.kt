package com.example.rshop.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rshop.data.model.ProductModel
import com.example.rshop.data.repository.ProductRepository
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {


    fun addFavoriteProduct(product: ProductModel) = viewModelScope.launch {
        productRepository.addFavoriteProduct(product)
    }

    fun deleteFavoriteProduct(product: ProductModel) = viewModelScope.launch {
        productRepository.deleteFavoriteProduct(product)
    }

    fun getFavoriteList() = productRepository.getFavoriteList()
}