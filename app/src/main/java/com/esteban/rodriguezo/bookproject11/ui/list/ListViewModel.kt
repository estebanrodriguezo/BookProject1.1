package com.esteban.rodriguezo.bookproject11.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esteban.rodriguezo.bookproject11.local.Book
import com.esteban.rodriguezo.bookproject11.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val bookRepository = BookRepository()

    private val loadBooks : MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    fun loadBooks() {
        GlobalScope.launch(Dispatchers.IO) {
            loadBooks.postValue(bookRepository.loadBooks())
        }
    }
}