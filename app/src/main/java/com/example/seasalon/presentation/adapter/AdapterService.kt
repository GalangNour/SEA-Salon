package com.example.seasalon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seasalon.databinding.ItemListServicesBinding
import com.example.seasalon.domain.model.Service

class AdapterService(private val serviceList: List<Service>?, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<AdapterService.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return serviceList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = serviceList?.get(position)
        if (service != null) {
            holder.bind(service)
            holder.itemView.setOnClickListener {
                itemClickListener.onItemClick(service)
            }
        }
    }

    inner class ViewHolder(private val binding: ItemListServicesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(service: Service) {
            binding.apply {
                Glide.with(root.context)
                    .load(service.image) // Load drawable resource by resource ID
                    .into(carouselImageView)
                carouselTitleTextView.text = service.name
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(service: Service)
    }
}
