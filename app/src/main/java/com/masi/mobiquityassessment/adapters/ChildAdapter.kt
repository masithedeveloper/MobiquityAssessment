package com.masi.mobiquityassessment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.masi.mobiquityassessment.BuildConfig
import com.masi.mobiquityassessment.data.responses.Products
import com.masi.mobiquityassessment.databinding.ChildRecyclerViewBinding
import javax.inject.Inject

class ChildAdapter @Inject constructor(private val list: List<Products>, private val glide: RequestManager) :
    RecyclerView.Adapter<ChildAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewDataBinding: ChildRecyclerViewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildAdapter.MyViewHolder {
        val binding =
            ChildRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildAdapter.MyViewHolder, position: Int) {

        holder.viewDataBinding.apply {
            productName.text = list[position].name
            loadImageThumb(ivThumb, list[position].url)
        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

    private fun loadImageThumb(ivThumb: ImageView, productImageUrl: String?) {
        productImageUrl?.let { url ->
            glide.load(BuildConfig.BASE_URL+url)?.into(ivThumb)
        }
    }
}