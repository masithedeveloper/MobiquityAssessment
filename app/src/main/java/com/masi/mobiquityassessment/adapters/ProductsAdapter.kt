package com.masi.mobiquityassessment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.masi.mobiquityassessment.data.responses.ProductSalesResponse
import com.masi.mobiquityassessment.databinding.ParentRecyclerViewBinding
import javax.inject.Inject

class ProductsAdapter @Inject constructor(private val glide: RequestManager) : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewDataBinding: ParentRecyclerViewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root){
            private val clickListener: TaskListRecyclerviewClickInterface? = null

        }

    private var products: ArrayList<ProductSalesResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.MyViewHolder {
        val binding =
            ParentRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.MyViewHolder, position: Int) {
       holder.viewDataBinding.section.text= products[position].name
        holder.viewDataBinding.childRecyclerView.apply {
            adapter = ChildAdapter(products[position].products, glide)

        }
    }

    override fun getItemCount(): Int {
       return products.size
    }

    fun submitList(products: List<ProductSalesResponse>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    interface TaskListRecyclerviewClickInterface {
        fun onItemClicked(position: Int, tag: String?)
    }

}