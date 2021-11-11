package com.masi.mobiquityassessment.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MobiquityFragmentFactory @Inject constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ListFragment::class.java.name -> ListFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}