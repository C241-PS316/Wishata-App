package com.capstone.wishata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.wishata.R
import com.capstone.wishata.data.network.response.WisataResponseItem
import com.capstone.wishata.databinding.WisataItemBinding

class HomeWisataAdapter : RecyclerView.Adapter<HomeWisataAdapter.WisataViewHolder>() {

    private var listWisata: List<WisataResponseItem?>? = emptyList()
    class WisataViewHolder(private val binding: WisataItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(wisataItem: WisataResponseItem) {
            Glide.with(binding.root)
                .load(wisataItem.photo)
                .error(R.drawable.ic_launcher_background)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WisataViewHolder {
        val binding = WisataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val wisataItem = listWisata?.get(position)
        if (wisataItem != null) {
            holder.bind(wisataItem)
        }
    }

    override fun getItemCount(): Int = listWisata?.size ?: 0


    fun setData(wisata: List<WisataResponseItem?>?) {
        listWisata = wisata
    }


}