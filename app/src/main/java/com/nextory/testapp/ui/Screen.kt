package com.nextory.testapp.ui

sealed class Screen(val route: String) {
    object BookList : Screen("book_list")
    object BookDetails: Screen("book_details")
}
