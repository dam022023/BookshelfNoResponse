package com.example.buscador_libros.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val items: List<Book>?,
    val totalItems: Int,
    val kind: String
)
