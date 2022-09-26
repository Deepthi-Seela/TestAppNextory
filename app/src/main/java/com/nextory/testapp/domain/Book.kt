package com.nextory.testapp.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.PrimaryKey

class Book (
    @PrimaryKey
    val id: Long,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    initialFavState: Boolean
) {
    var isFavorite: Boolean by mutableStateOf(initialFavState)
}