package com.capstone.wishata.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.wishata.data.network.response.WisataResponse
import com.capstone.wishata.databinding.FragmentDetailWisataBinding


class DetailWisataFragment : Fragment() {

    private var _binding: FragmentDetailWisataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailWisataBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wisataItem = arguments?.getParcelable<WisataResponse.WisataItem>(EXTRA_WISATA)


        binding.textTitle.text = wisataItem?.name ?: "Judul"
        binding.textDescription.text = wisataItem?.description ?: "Description"
        binding.ratingText.text = wisataItem?.rating.toString()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_WISATA = "extra_wisata"

    }
}