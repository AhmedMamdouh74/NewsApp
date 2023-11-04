package com.example.domin.entities

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SourcesItemDTO(


	var country: String? = null,


	var name: String? = null,


	var description: String? = null,

	var language: String? = null,


	var id: String ,

	var category: String? = null,

	var url: String? = null
) : Parcelable
@Parcelize
data class SourcesResponseDTO(

	val sources: List<SourcesItemDTO>? = null,

	val status: String? = null
) : Parcelable