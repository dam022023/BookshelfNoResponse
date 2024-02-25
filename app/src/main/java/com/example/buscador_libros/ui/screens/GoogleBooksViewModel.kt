package com.example.buscador_libros.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buscador_libros.data.NetworkGoogleBooksRepository
import com.example.buscador_libros.model.Book
import com.example.buscador_libros.model.GoogleBookResponse
import com.example.buscador_libros.network.BooksApi
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class GoogleBooksViewModel : ViewModel() {
    private val _searchQuery = MutableLiveData<String>()
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books


    fun searchBooks(query: String) {
        viewModelScope.launch {
            val response =
                BooksApi.retrofitService.getBooks(books.value.toString())
            _books.value = response.items
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

sealed interface BooksUiState{
    data class Success(val volumes: GoogleBookResponse): BooksUiState
    object Error: BooksUiState
    object Loading: BooksUiState
}