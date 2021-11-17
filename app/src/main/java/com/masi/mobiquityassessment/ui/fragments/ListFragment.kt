package com.masi.mobiquityassessment.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.masi.mobiquityassessment.R
import com.masi.mobiquityassessment.adapters.ProductsAdapter
import com.masi.mobiquityassessment.data.responses.Status
import com.masi.mobiquityassessment.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment @Inject constructor(val productsAdapter: ProductsAdapter, var viewModel: ListViewModel? = null) : Fragment(R.layout.fragment_list){

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        subscribeToObservers()
        setupRecyclerView()
        viewModel?.getProducts()
    }

    private fun subscribeToObservers() {
        viewModel?.products?.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { productList -> productsAdapter.submitList(productList) }
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "An unknown error occurred.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

        viewModel?.selectedProduct?.observe(viewLifecycleOwner){
            if(it.id != 0 && !it.name.isNullOrBlank()) {
                navigateToDetailFragment()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.listRecyclerView.apply {
//            productsAdapter.setOnItemClickListener {
//                Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
//            viewModel?.selectedProduct.value = it
//            }
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetailFragment() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.action_listFragment_to_detailFragment)
    }
}