package com.example.rshop.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rshop.data.model.ProductModel
import com.example.rshop.data.repository.ProductRepository
import com.example.rshop.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {


    val getFiveProductList: MutableLiveData<Resource<List<ProductModel>>> = MutableLiveData()

    init {
        getFiveProduct()
    }

     fun getFiveProduct() = viewModelScope.launch {
        getFiveProductList.postValue(handleResponse(productRepository.getFiveProduct()))
    }

    private fun handleResponse(response: Resource<List<ProductModel>>) =
        when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
}