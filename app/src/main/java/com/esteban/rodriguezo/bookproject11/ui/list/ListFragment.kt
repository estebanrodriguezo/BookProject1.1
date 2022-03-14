package com.esteban.rodriguezo.bookproject11.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esteban.rodriguezo.bookproject11.databinding.FragmentListBinding
import com.esteban.rodriguezo.bookproject11.local.Book
import com.esteban.rodriguezo.bookproject11.server.BookServer

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel
    private lateinit var booksAdapter: BooksAdapter
    private var booksList: ArrayList<Book> = ArrayList()    //room
    private var booksListFromServer: ArrayList<BookServer> = ArrayList()    //firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.loadBooksDone.observe(viewLifecycleOwner) { result ->
            onLoadBooksDoneSubscribe(result)
        }

        listViewModel.loadBooksFromServerDone.observe(viewLifecycleOwner) { result ->
            onLoadBooksFromServerDoneSubscribe(result)
        }

        //listViewModel.loadBooks()
        listViewModel.loadBooksFromServer()

        booksAdapter = BooksAdapter(booksListFromServer, onItemClicked = { onBookItemClicked(it)})

        listBinding.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = booksAdapter
            setHasFixedSize(false)
        }

        listBinding.newButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
        }
    }

    private fun onBookItemClicked(book: BookServer) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
    }

    private fun onLoadBooksFromServerDoneSubscribe(booksListFromServerLoaded: ArrayList<BookServer>) { //firebase
        booksListFromServer = booksListFromServerLoaded
        booksAdapter.appendItems(booksListFromServer)
    }

    private fun onLoadBooksDoneSubscribe(booksListLoaded: ArrayList<Book>) { //room
        booksList = booksListLoaded
        //    booksAdapter.appendItems(booksList)
    }
}