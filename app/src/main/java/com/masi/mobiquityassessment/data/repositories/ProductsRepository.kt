package com.masi.mobiquityassessment.data.repositories

import com.masi.mobiquityassessment.data.IMobiquityAPI
import com.masi.mobiquityassessment.data.responses.Constants.NO_INTERNET
import com.masi.mobiquityassessment.data.responses.Constants.SOMETHING_WENT_WRONG
import com.masi.mobiquityassessment.data.responses.ProductSalesReponse
import com.masi.mobiquityassessment.data.responses.Resource
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val mobiquityAPI: IMobiquityAPI) :
    IProductsRepository {

    /**
     * Get products
     */
    override suspend fun getProducts(): Resource<List<ProductSalesReponse>> {
        return try {
            val response = mobiquityAPI.getProducts()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(SOMETHING_WENT_WRONG, null)
            } else {
                Resource.error(SOMETHING_WENT_WRONG, null)
            }
        } catch (e: Exception) {
            Resource.error(NO_INTERNET, null)
        }
    }
}










