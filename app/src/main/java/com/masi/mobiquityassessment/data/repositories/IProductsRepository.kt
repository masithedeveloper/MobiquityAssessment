package com.masi.mobiquityassessment.data.repositories

import com.masi.mobiquityassessment.data.responses.ProductSalesResponse
import com.masi.mobiquityassessment.data.responses.Resource

interface IProductsRepository {

    suspend fun getProducts(): Resource<List<ProductSalesResponse>>
}