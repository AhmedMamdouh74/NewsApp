package com.example.data.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
@Parcelize
data class SourcesItem(

	@field:SerializedName("country")
	var country: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("description")
	var description: String? = null,

	@field:SerializedName("language")
	var language: String? = null,

	@PrimaryKey(false)
	@field:SerializedName("id")
	var id: String ,

	@field:SerializedName("category")
	var category: String? = null,

	@field:SerializedName("url")
	var url: String? = null
) : Parcelable
@Parcelize
data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<SourcesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable