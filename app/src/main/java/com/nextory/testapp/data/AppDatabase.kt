package com.nextory.testapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BookDto::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}