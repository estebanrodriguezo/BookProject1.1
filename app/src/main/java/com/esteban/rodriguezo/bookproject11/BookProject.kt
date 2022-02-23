package com.esteban.rodriguezo.bookproject11

import android.app.Application
import androidx.room.Room
import com.esteban.rodriguezo.bookproject11.local.BookDatabase

class BookProject : Application() {

    companion object {
        lateinit var database: BookDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            BookDatabase::class.java,
            "book_db"
        ).build()
    }

}
