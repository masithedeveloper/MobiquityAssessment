package com.masi.mobiquityassessment.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.masi.mobiquityassessment.R
import com.masi.mobiquityassessment.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment @Inject constructor() : Fragment(R.layout.fragment_detail){

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
    }

    private fun subscribeToObservers() {
        viewModel?.products?.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}