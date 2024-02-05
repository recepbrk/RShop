package com.example.rshop.ui.fragment.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rshop.data.repository.ProductRepository
import com.example.rshop.data.source.local.basket.BasketEntity
import com.example.rshop.data.source.local.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BasketViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {

    fun addBasketProduct(product: BasketEntity) = viewModelScope.launch {
        productRepository.addBasketProduct(product)
    }

    fun deleteBasketProduct(product: BasketEntity) = viewModelScope.launch {
        productRepository.deleteBasketProduct(product)
    }

    fun getBasketList() = productRepository.getBasketList()
}
