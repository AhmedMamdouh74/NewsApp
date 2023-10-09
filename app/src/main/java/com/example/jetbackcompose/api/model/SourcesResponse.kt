package com.example.newsapp.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<Sources>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable