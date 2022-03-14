package com.esteban.rodriguezo.bookproject11.ui.list

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

class ListViewModel : ViewModel() {

    val bookRepository = BookRepository()
    val bookServerRepository = BookServerRepository()

    private var booksList: ArrayList<BookServer> = ArrayList()

    private val loadBooks: MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    private val loadBooksFromServer: MutableLiveData<ArrayList<BookServer>> = MutableLiveData()
    val loadBooksFromServerDone: LiveData<ArrayList<BookServer>> = loadBooksFromServer

    fun loadBooks() {
        GlobalScope.launch(Dispatchers.IO) {
            loadBooks.postValue(bookRepository.loadBooks())
        }
    }

    fun loadBooksFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            val querySnapshot = bookServerRepository.loadBooks()
            for (document in querySnapshot) {
                val bookServer: BookServer = document.toObject<BookServer>()
                booksList.add(bookServer)
            }
            loadBooksFromServer.postValue(booksList)
        }
    }
}