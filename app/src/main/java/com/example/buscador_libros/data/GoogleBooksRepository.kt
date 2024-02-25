package com.example.buscador_libros.data

import com.example.buscador_libros.model.GoogleBookResponse
import com.example.buscador_libros.network.BooksApi
import retrofit2.Response

interface GoogleBooksRepository{
    suspend fun getGoogleBooks(query: String): GoogleBookResponse
}

class NetworkGoogleBooksRepository: GoogleBooksRepository{
    override suspend fun getGoogleBooks(query: String): GoogleBookResponse {
        return BooksApi.retrofitService.getBooks(query)
    }
}

