package com.nextory.testapp.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }
}