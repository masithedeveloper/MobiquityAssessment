package com.masi.mobiquityassessment.data

import com.masi.mobiquityassessment.data.responses.ProductSalesResponse
import retrofit2.Response
import retrofit2.http.GET

interface IMobiquityAPI {

    @GET(".")
    suspend fun getProducts(): Response<List<ProductSalesResponse>>
}