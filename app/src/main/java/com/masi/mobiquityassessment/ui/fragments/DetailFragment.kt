package com.masi.mobiquityassessment.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.masi.mobiquityassessment.BuildConfig
import com.masi.mobiquityassessment.R
import com.masi.mobiquityassessment.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment @Inject constructor(private val glide: RequestManager) : Fragment(R.layout.fragment_detail){

    lateinit var viewModel: ListViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        subscribeToObservers()

        binding.backToList.setOnClickListener {
            handleBackNavigation()
        }
    }

    private fun subscribeToObservers() {
        viewModel?.selectedProduct?.observe(viewLifecycleOwner) {
            binding.productName.text = it.name
            glide.load(BuildConfig.BASE_URL+it.url)?.into(binding.ivThumb)
            binding.thePriceOfProduct.text = it.salePrice.currency +" "+ it.salePrice.amount
        }
    }

    private fun handleBackNavigation() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}