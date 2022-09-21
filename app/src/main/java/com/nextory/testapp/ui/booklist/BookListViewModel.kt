package com.nextory.testapp.ui.booklist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)
    var book by mutableStateOf(Book(0,"","","",""))

    fun getBook(id: Long) = viewModelScope.launch {
        bookRepository.getBookById(id).collect { dbBook ->
            book = dbBook
        }
    }
    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }
}