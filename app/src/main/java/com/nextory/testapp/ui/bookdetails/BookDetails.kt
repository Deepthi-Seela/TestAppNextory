package com.nextory.testapp.ui.bookdetails

import android.util.Log
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
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(bookId)
    }
    Scaffold(
        topBar = {
            BookDetailsTopBar(
                book = viewModel.book,
                isFavorite = viewModel.isFavorite(bookId),
                onCheckedChanged = { checked -> viewModel.addOrRemoveFavorite(bookId, checked) },
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            BookDetailsContent(
                padding = padding,
                book = viewModel.book
            )
        }
    )
}