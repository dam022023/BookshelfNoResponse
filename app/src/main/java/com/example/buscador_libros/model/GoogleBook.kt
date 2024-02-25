package com.example.buscador_libros.model

import kotlinx.serialization.Serializable

@Serializable
data class GoogleBookResponse(
    val items: List<Book>? = null
)

@Serializable
data class Book(
    val volumeInfo: VolumeInfo? = null
)

@Serializable
data class VolumeInfo(
    val title: String? = null,
    val authors: List<String>? = null,
    val imageLinks: Image? = null,
    val publisher: String? = null,
    val publishedDate: String? = null
)

@Serializable
data class Image(
    val thumbnail: String? = null
)