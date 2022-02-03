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
import com.esteban.rodriguezo.bookproject11.model.Book

class ListFragment : Fragment() {

    private lateinit var listBindig: FragmentListBinding
    private lateinit var listViewModel: ListViewModel
    private lateinit var booksAdapter: BooksAdapter
    private var bookList: ArrayList<Book> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBindig = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        return listBindig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookList.add(
            Book(
                "El sicoanalista",
                "Jhon Katzenbach",
                280,
                "Hola Doctor",
                "Suspenso",
                5,
                "10-ene-2000"
            )
        )
        bookList.add(
            Book(
                "La chica del tren",
                "Paula Hawkins",
                350,
                "Una chica en un tren",
                "Suspenso",
                4,
                "1-feb-2018"
            )
        )
        bookList.add(
            Book(
                "El principito",
                "Antony Exupery",
                150,
                "Erase una vez un prinncipito",
                "Fantasia",
                3,
                "10-may-1980"
            )
        )

        booksAdapter = BooksAdapter(bookList)

        listBindig.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = booksAdapter
            setHasFixedSize(false)
        }

        listBindig.newButton.setOnClickListener {

            findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
        }
    }

}
