package com.capstone.wishata.ui.wishlist

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.wishata.data.local.database.entity.Place
import com.capstone.wishata.databinding.ItemPlaceBinding
import com.capstone.wishata.ui.wishlist.WishlistAdapter.MyViewHolder
import java.io.IOException
import java.util.Locale

class WishlistAdapter: ListAdapter<Place, MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)
    }

    class MyViewHolder(val binding: ItemPlaceBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            Glide.with(binding.cardView)
                .load(place.photoUrl)
                .into(binding.imgStory)
            binding.titleStory.text = place.namePlace
            binding.ratingPlace.text = place.rating.toString()
            binding.address.text = getNameLocation(place.lat, place.lon)
        }

        private fun getNameLocation(lat: Double, lon: Double):String {
            val geocoder = Geocoder(context, Locale.getDefault())
            return try {
                val addresses = geocoder.getFromLocation(lat, lon, 1)
                if (addresses!!.isNotEmpty()) {
                    val address = addresses[0]
                    address.subAdminArea
                } else {
                    ""
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
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