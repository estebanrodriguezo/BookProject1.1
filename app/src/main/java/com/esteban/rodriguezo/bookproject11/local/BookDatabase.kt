package com.esteban.rodriguezo.bookproject11.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {

    abstract fun BookDao(): BookDao

}