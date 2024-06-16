package com.capstone.wishata.ui.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.wishata.data.local.database.entity.Place
import com.capstone.wishata.databinding.ItemPlaceBinding
import com.capstone.wishata.ui.wishlist.WishlistAdapter.MyViewHolder

class WishlistAdapter: ListAdapter<Place, MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)
    }

    class MyViewHolder(val binding: ItemPlaceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            // bind all data
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Place> =
            object : DiffUtil.ItemCallback<Place>() {
                override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
                    return oldItem == newItem
                }
            }
    }
}