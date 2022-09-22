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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)
    var book by mutableStateOf(Book(0,"","","",""))
    var booksList by mutableStateOf<List<Book>>(listOf())
    var isSearching = mutableStateOf(false)

    private var cachedBooks = listOf<Book>()
    private var isSearchStarting = true


    fun getBook(id: Long) = viewModelScope.launch {
        bookRepository.getBookById(id).collect { dbBook ->
            book = dbBook
        }
    }

    fun getBooks() = viewModelScope.launch {
        bookRepository.getAllBooks().collect {dbBooks ->
            booksList = dbBooks
        }
    }
    fun searchBookList(query: String) {
        val listToSearch = if(isSearchStarting) {
            booksList
        } else {
            cachedBooks
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                booksList = cachedBooks
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.title.contains(query.trim(), ignoreCase = true) ||
                        it.author.contains(query.trim(), ignoreCase = true)
            }
            if (isSearchStarting) {
                cachedBooks = booksList
                isSearchStarting = false
            }
            booksList = results
            isSearching.value = true
        }
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }
}