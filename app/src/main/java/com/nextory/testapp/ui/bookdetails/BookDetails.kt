package com.nextory.testapp.ui.bookdetails

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.nextory.testapp.ui.bookdetails.components.BookDetailsContent
import com.nextory.testapp.ui.bookdetails.components.BookDetailsTopBar
import com.nextory.testapp.ui.booklist.BookListViewModel

@Composable
fun BookDetails(
    viewModel: BookListViewModel = hiltViewModel(),
    bookId: Long,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }

    val book = viewModel.book
    Scaffold(
        topBar = {
            BookDetailsTopBar(
                title = book.title,
                isFavorite = book.isFavorite,
                onCheckedChanged = { checked -> viewModel.addOrRemoveFavorite(book, checked) },
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            BookDetailsContent(
                padding = padding,
                book = book
            )
        }
    )
}