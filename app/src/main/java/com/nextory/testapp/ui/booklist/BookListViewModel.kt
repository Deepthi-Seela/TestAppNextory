package com.nextory.testapp.ui.booklist

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.map
import com.nextory.testapp.data.BookRepository
import com.nextory.testapp.domain.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)
    var book by mutableStateOf(Book(0, "", "", "", "", false))

    var booksList by mutableStateOf<List<Book>>(listOf())
    var isSearching = mutableStateOf(false)

    private var cachedBooks = listOf<Book>()
    private var isSearchStarting = true

    fun addOrRemoveFavorite(book: Book, checked: Boolean) {
        /** FIX ME :Tried to update list with updated isFavorite value. But somehow it's not working*/
//        pagedBooks.map { list ->
//            list.map {
//                if (it.id == bookId) {
//                    it.isFavorite = !it.isFavorite
//                }
//            }
//            return@map
//        }
        book.isFavorite = !book.isFavorite
    }

    fun getBook(id: Long) = viewModelScope.launch(Dispatchers.Default) {
        bookRepository.getBookById(id).collect { dbBook ->
            book = dbBook
        }
    }

    fun getBooks() = viewModelScope.launch(Dispatchers.Default) {
        bookRepository.getAllBooks().collect { dbBooks ->
            booksList = dbBooks
        }
    }

    fun searchBookList(query: String) {
        val listToSearch = if (isSearchStarting) {
            booksList
        } else {
            cachedBooks
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
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