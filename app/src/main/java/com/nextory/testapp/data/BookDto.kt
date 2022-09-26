package com.nextory.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nextory.testapp.domain.Book

@Entity(tableName = "book")
data class BookDto(
    @PrimaryKey val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String
)

fun BookDto.toBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        description = description,
        imageUrl = imageUrl,
        initialFavState = false
    )
}
