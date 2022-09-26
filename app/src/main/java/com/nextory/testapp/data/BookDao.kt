package com.nextory.testapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun observePagedBooks(): PagingSource<Int, BookDto>

    @Query("SELECT * FROM book WHERE id = :book_id LIMIT 1")
    fun getBookById(book_id: Long): Flow<BookDto>

    @Query("SELECT * FROM book")
    fun getAllBooks(): Flow<List<BookDto>>
}