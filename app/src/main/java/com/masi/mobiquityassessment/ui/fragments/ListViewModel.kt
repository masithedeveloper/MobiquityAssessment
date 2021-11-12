package com.masi.mobiquityassessment.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masi.mobiquityassessment.data.repositories.ProductsRepository
import com.masi.mobiquityassessment.data.responses.Event
import com.masi.mobiquityassessment.data.responses.ProductSalesReponse
import com.masi.mobiquityassessment.data.responses.Products
import com.masi.mobiquityassessment.data.responses.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val productRepository: ProductsRepository
) : ViewModel() {

    private val _selectedProduct = MutableLiveData<Products>()
    val selectedProduct: LiveData<Products> = _selectedProduct
    private val _products = MutableLiveData<Event<Resource<List<ProductSalesReponse>>>>()
    val products: MutableLiveData<Event<Resource<List<ProductSalesReponse>>>> = _products

    fun getProducts() {
        _products.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = productRepository.getProducts()
            products.value = Event(response)
        }
    }
}