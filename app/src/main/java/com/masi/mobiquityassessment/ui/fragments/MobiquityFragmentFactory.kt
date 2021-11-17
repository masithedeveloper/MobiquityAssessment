package com.masi.mobiquityassessment.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.masi.mobiquityassessment.adapters.ProductsAdapter
import javax.inject.Inject

class MobiquityFragmentFactory @Inject constructor(
    private val productsAdapter: ProductsAdapter, private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ListFragment::class.java.name -> ListFragment(productsAdapter)
            DetailFragment::class.java.name -> DetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}