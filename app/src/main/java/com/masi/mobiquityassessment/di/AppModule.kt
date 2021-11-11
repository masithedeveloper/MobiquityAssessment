package com.masi.mobiquityassessment.di

import com.masi.mobiquityassessment.BuildConfig
import com.masi.mobiquityassessment.data.IMobiquityAPI
import com.masi.mobiquityassessment.data.repositories.IProductsRepository
import com.masi.mobiquityassessment.data.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductsRepository(mobiquityAPI: IMobiquityAPI) =
        ProductsRepository(mobiquityAPI) as IProductsRepository

    @Singleton
    @Provides
    fun provideMobiquityAPI(): IMobiquityAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(IMobiquityAPI::class.java)
    }
}

















