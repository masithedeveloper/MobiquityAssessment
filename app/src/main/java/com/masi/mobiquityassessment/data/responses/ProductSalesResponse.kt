package com.masi.mobiquityassessment.data.responses

import com.google.gson.annotations.SerializedName

data class ProductSalesResponse (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("products") val products : List<Products>
)

data class Products (

	@SerializedName("id") val id : Int,
	@SerializedName("categoryId") val categoryId : Int,
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String,
	@SerializedName("description") val description : String,
	@SerializedName("salePrice") val salePrice : SalePrice
)

data class SalePrice (

	@SerializedName("amount") val amount : Double,
	@SerializedName("currency") val currency : String
)
