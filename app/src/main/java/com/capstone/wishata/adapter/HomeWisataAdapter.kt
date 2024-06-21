package com.capstone.wishata.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.wishata.R
import com.capstone.wishata.data.local.database.entity.Place
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.databinding.WisataItemBinding
import com.capstone.wishata.repository.WishataRepository
import com.capstone.wishata.ui.detail.DetailWisataFragment
import com.capstone.wishata.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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

            itemView.setOnClickListener {
                val bundle = Bundle()
                Toast.makeText(itemView.context, "${wisataItem.name}", Toast.LENGTH_SHORT).show()
                bundle.putParcelable(DetailWisataFragment.EXTRA_WISATA, wisataItem)
                Navigation.findNavController(itemView).navigate(R.id.action_navigation_home_to_detailWisataFragment)
            }

            // Trying save to room
            binding.favoriteButton.setOnClickListener {
                showToast("${ wisataItem.name } added to Wishlist", context = itemView.context)
            }
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