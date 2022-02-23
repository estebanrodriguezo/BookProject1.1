package com.esteban.rodriguezo.bookproject11.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esteban.rodriguezo.bookproject11.R
import com.esteban.rodriguezo.bookproject11.databinding.FragmentDeleteBinding
import com.esteban.rodriguezo.bookproject11.local.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteFragment : Fragment() {

    private lateinit var deleteBinding: FragmentDeleteBinding
    private lateinit var deleteViewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        deleteBinding = FragmentDeleteBinding.inflate(inflater, container, false)
        deleteViewModel = ViewModelProvider(this)[DeleteViewModel::class.java]
        return deleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteViewModel.findBookDone.observe(viewLifecycleOwner, {result ->
            onFindBookDoneSubscribe(result)
        })

        with(deleteBinding) {
            searchButton.setOnClickListener {
                deleteViewModel.searchBook(nameEditText.text.toString())
            }
        }
    }

    private fun onFindBookDoneSubscribe(book: Book) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.warning_title))
            .setMessage(resources.getString(R.string.delete_book_msg, book.name, book.author))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                deleteViewModel.deleteBook(book)
                deleteBinding.nameEditText.text?.clear()
            }
            .show()
    }

}