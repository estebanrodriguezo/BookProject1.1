package com.esteban.rodriguezo.bookproject11.ui.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esteban.rodriguezo.bookproject11.R
import com.esteban.rodriguezo.bookproject11.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var updateBinding: FragmentUpdateBinding
    private lateinit var updateViewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        return updateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}