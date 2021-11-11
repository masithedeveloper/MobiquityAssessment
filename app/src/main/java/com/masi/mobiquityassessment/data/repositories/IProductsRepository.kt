package com.masi.mobiquityassessment.data.repositories

import com.masi.mobiquityassessment.data.responses.ProductSalesReponse
import com.masi.mobiquityassessment.data.responses.Resource

interface IProductsRepository {

    suspend fun getProducts(): Resource<List<ProductSalesReponse>>
}