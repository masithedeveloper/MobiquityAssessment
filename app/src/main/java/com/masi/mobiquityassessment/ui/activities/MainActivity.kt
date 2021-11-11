package com.masi.mobiquityassessment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masi.mobiquityassessment.R
import com.masi.mobiquityassessment.ui.fragments.MobiquityFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: MobiquityFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}