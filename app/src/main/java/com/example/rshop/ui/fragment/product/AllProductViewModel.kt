package com.example.rshop.ui.fragment.product
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
class AllProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    val getProductList: MutableLiveData<Resource<List<ProductModel>>> = MutableLiveData()

    init {
        getSpecificCategory()
    }
    fun getSpecificCategory() = viewModelScope.launch{
        getProductList.postValue(handleResponse(productRepository.getAllProduct()))
    }

    private fun handleResponse(response: Resource<List<ProductModel>>) =
        when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
}