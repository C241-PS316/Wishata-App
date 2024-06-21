package com.capstone.wishata.data.network.response

import com.google.gson.annotations.SerializedName

data class TopWisataResponse(

	@field:SerializedName("data")
	val data: List<TopWisataItem> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) {

	data class TopWisataItem(

		@field:SerializedName("photoURL")
		val photoURL: String? = null,

		@field:SerializedName("name")
		val name: String? = null,

		@field:SerializedName("rating")
		val rating: Any? = null,

		@field:SerializedName("category_classes")
		val categoryClasses: String? = null,

		@field:SerializedName("description")
		val description: String? = null,

		@field:SerializedName("lon")
		val lon: Any? = null,

		@field:SerializedName("id")
		val id: String? = null,

		@field:SerializedName("lat")
		val lat: Any? = null,

		@field:SerializedName("scenery_classes")
		val sceneryClasses: String? = null,

		@field:SerializedName("environment_classes")
		val environmentClasses: String? = null
	)
}


