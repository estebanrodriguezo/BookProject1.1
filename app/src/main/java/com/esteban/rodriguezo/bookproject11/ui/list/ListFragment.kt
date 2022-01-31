package com.esteban.rodriguezo.bookproject11.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.esteban.rodriguezo.bookproject11.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listBindig: FragmentListBinding
    private lateinit var listViewModel: ListViewModel

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

        listBindig.newButton.setOnClickListener{

         findNavController().navigate(ListFragmentDirections.actionListFragmentToNewBookFragment())
        }
    }

}
