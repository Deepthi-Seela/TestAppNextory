package com.nextory.testapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nextory.testapp.domain.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow.map { list ->
            list.map { bookDto -> bookDto.toBook() }
        }
    }

    fun getBookById(id: Long): Flow<Book> {
        return bookDao.getBookById(id).map { bookDto -> bookDto.toBook() }
    }

    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks().map { list -> list.map { bookDto -> bookDto.toBook() } }
    }
}