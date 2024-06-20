package com.capstone.wishata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.wishata.R
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.databinding.WisataItemBinding

class HomeWisataAdapter(
    private val listWisata: ArrayList<WisataResponse.WisataItem> = arrayListOf()
) : RecyclerView.Adapter<HomeWisataAdapter.WisataViewHolder>() {

    class WisataViewHolder(private val binding: WisataItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(wisataItem: WisataResponse.WisataItem) {
            Glide.with(binding.root)
                .load(wisataItem.photoURL)
                .error(R.drawable.ic_launcher_background)
                .into(binding.image)

            binding.textTitle.text = wisataItem.name
            binding.textRating.text = wisataItem.rating.toString()

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
        val wisataItem = listWisata[position]
        holder.bind(wisataItem)
    }

    override fun getItemCount(): Int = listWisata.size


    fun setData(wisata: List<WisataResponse.WisataItem>) {
        this.listWisata.clear()
        this.listWisata.addAll(wisata)
    }

}