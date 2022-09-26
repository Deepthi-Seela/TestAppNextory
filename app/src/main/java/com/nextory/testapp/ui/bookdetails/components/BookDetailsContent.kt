package com.nextory.testapp.ui.bookdetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nextory.testapp.domain.Book


@Composable
fun BookDetailsContent(
    padding: PaddingValues,
    book: Book
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = book.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(5.dp)),
            alignment = Alignment.Center
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = book.author
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Text(
            text = book.description,
            modifier = Modifier.padding(10.dp)
        )
    }
}