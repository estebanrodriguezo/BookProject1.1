package com.esteban.rodriguezo.bookproject11.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esteban.rodriguezo.bookproject11.local.Book
import com.esteban.rodriguezo.bookproject11.local.localrepository.BookRepository
import com.esteban.rodriguezo.bookproject11.server.BookServer
import com.esteban.rodriguezo.bookproject11.server.serverrepository.BookServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    val bookRepository = BookRepository()
    val bookServerRepository = BookServerRepository()

    private val findBook: MutableLiveData<Book> = MutableLiveData()
    val findBookDone: LiveData<Book> = findBook

    private val findBookServer: MutableLiveData<BookServer?> = MutableLiveData()
    val findBookServerDone: LiveData<BookServer?> = findBookServer

    fun searchBook(nameBook: String) {
        GlobalScope.launch(Dispatchers.IO) {
            //findBook.postValue(bookRepository.searchBook(nameBook))
            val result = bookServerRepository.loadBooks()
            var isFoundBook = false
            for (document in result) {
                val bookServer: BookServer = document.toObject<BookServer>()
                if (nameBook == bookServer.name) {
                    findBookServer.postValue(bookServer)
                    isFoundBook = true
                }
            }
            if (!isFoundBook) findBookServer.postValue(null)
        }
    }

    fun deleteBook(book: Book) {
        GlobalScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }

    fun deleteBookServer(book: BookServer) {
        GlobalScope.launch(Dispatchers.IO) {
            bookServerRepository.deleteBook(book)
        }
    }
}