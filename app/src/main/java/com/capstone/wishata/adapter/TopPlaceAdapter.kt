package com.capstone.wishata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.wishata.R
import com.capstone.wishata.data.network.response.TopWisataResponse
import com.capstone.wishata.databinding.ItemTopPlaceBinding

class TopPlaceAdapter(
    private val listTopPlace: ArrayList<TopWisataResponse.TopWisataItem> = arrayListOf()
) : RecyclerView.Adapter<TopPlaceAdapter.TopPlaceViewHolder>() {

    class TopPlaceViewHolder(private val binding: ItemTopPlaceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(topPlaceItem: TopWisataResponse.TopWisataItem) {
            Glide.with(binding.root)
                .load(topPlaceItem.photoURL)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageTopPlace)

            binding.textTitleTopPlace.text = topPlaceItem.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopPlaceViewHolder {
        val binding = ItemTopPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopPlaceViewHolder, position: Int) {
        val topPlaceItem = listTopPlace[position]
        holder.bind(topPlaceItem)
    }

    override fun getItemCount(): Int = listTopPlace.size

    /// pass data to adapter
    fun setData(topPlace: List<TopWisataResponse.TopWisataItem>) {
        this.listTopPlace.clear()
        this.listTopPlace.addAll(topPlace)
    }

}