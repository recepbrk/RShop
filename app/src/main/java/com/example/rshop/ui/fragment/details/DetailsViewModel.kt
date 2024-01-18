package com.example.rshop.ui.fragment.details

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
class ProductDetailsViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {
    val getdetailsList:MutableLiveData<Resource<ProductModel>> = MutableLiveData()

    fun getDetails(id:Int) = viewModelScope.launch {

        getdetailsList.postValue(productRepository.getSingleProduct(id))
    }

}