package com.capstone.wishata.data.network.response

import com.google.gson.annotations.SerializedName

data class WisataResponse(

	@field:SerializedName("WisataResponse")
	val wisataResponse: List<WisataResponseItem>
)

data class WisataResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("environment")
	val environment: String? = null,

	@field:SerializedName("scenery")
	val scenery: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
