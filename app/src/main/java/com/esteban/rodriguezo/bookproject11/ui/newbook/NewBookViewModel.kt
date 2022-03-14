package com.esteban.rodriguezo.bookproject11.ui.newbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esteban.rodriguezo.bookproject11.local.localrepository.BookRepository
import com.esteban.rodriguezo.bookproject11.server.serverrepository.BookServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewBookViewModel : ViewModel() {

    private val bookRepository = BookRepository()
    private val bookServerRepository = BookServerRepository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    fun validateFields(nameBook: String, nameAuthor: String, pages: String) {
        if (nameBook.isEmpty() || nameAuthor.isEmpty() || pages.isEmpty()) {
            msg.value = "Debe digitar nombre, autor y número de páginas"
        } else {
            dataValidate.value = true
        }
    }

    fun saveBook(
        nameBook: String,
        author: String,
        pages: Int,
        resume: String,
        genre: String,
        score: Int,
        publicationDate: String
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            bookRepository.saveBook(nameBook, author, pages, resume, genre, score, publicationDate)
        }
    }

    fun saveBookInServer(nameBook: String, author: String, pages: Int, resume: String, genre: String, score: Int, publicationDate: String) {
        GlobalScope.launch(Dispatchers.IO){
            bookServerRepository.saveBook(nameBook, author, pages, resume, genre, score, publicationDate)
        }
    }
}