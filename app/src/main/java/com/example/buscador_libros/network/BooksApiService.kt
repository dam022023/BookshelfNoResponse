package com.example.buscador_libros.network

import com.example.buscador_libros.model.GoogleBookResponse
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"


private val json= Json{
    ignoreUnknownKeys=true
}

private val contentType = "application/json".toMediaType()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()


interface BooksApiService{
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): GoogleBookResponse
}

object BooksApi{
    val retrofitService: BooksApiService by lazy{
        retrofit.create(BooksApiService::class.java)
    }
}