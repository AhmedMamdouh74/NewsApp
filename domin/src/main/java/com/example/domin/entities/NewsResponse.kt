package com.example.domin.entities

import kotlinx.parcelize.Parcelize
import android.os.Parcelable



@Parcelize
data class NewsResponseDTO(


    val totalResults: Int? = null,


    val articles: List<ArticlesItemDTO>? = null,


    val status: String? = null
) : Parcelable

@Parcelize
data class SourceDTO(


    val name: String? = null,


    val id: String? = null
) : Parcelable


@Parcelize
data class ArticlesItemDTO(

    var id: Int? = null,


    var publishedAt: String? = null,


    var author: String? = null,


    var urlToImage: String? = null,


    var description: String? = null,

    var source: SourceDTO? = null,


    var title: String? = null,


    var url: String? = null,


    var content: String? = null
) : Parcelable
