package com.masi.mobiquityassessment.di

import android.content.Context
import android.graphics.PointF
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.masi.mobiquityassessment.BuildConfig
import com.masi.mobiquityassessment.R
import com.masi.mobiquityassessment.data.IMobiquityAPI
import com.masi.mobiquityassessment.data.repositories.IProductsRepository
import com.masi.mobiquityassessment.data.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

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

















