package com.esteban.rodriguezo.bookproject11.ui.newbook

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.esteban.rodriguezo.bookproject11.databinding.FragmentNewBookBinding
import java.text.SimpleDateFormat
import java.util.*


class NewBookFragment : Fragment() {

    private lateinit var newBookBinding: FragmentNewBookBinding
    private lateinit var newBookViewModel: NewBookViewModel
    private var cal = Calendar.getInstance()
    private var publicationDate = ""

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        newBookBinding = FragmentNewBookBinding.inflate(inflater, container, false)
        newBookViewModel = ViewModelProvider(this).get(NewBookViewModel::class.java)
        return newBookBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newBookViewModel.msgDone.observe(viewLifecycleOwner) { result ->
            onMsgDoneSubscribe(result)
        }

        newBookViewModel.dataValidated.observe(viewLifecycleOwner) { result ->
            onDataValidatedSubscribe(result)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            newBookBinding.publicationDateButton.text = publicationDate
        }

        with(newBookBinding) {

            publicationDateButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {
                newBookViewModel.validateFields(
                    nameBookEditText.text.toString(),
                    nameAuthorEditText.text.toString(),
                    pagesEditText.text.toString()
                )
            }

            photoImageView.setOnClickListener {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){ //proviene de la camara
            val imageBitmap = data?.extras?.get("data") as Bitmap
            newBookBinding.photoImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun onDataValidatedSubscribe(result: Boolean?) {
        with(newBookBinding) {
            val nameBook: String = nameBookEditText.text.toString()
            val author = nameAuthorEditText.text.toString()
            val pages = pagesEditText.text.toString().toInt()
            val resume = abstractEditText.text.toString()

            var genre = ""

            if (suspenseCheckBox.isChecked) genre += "Suspenso"
            if (fictionCheckBox.isChecked) genre += "Ficcion"
            if (terrorCheckBox.isChecked) genre += "Terror"
            if (infantileCheckBox.isChecked) genre += "Infantil"

            val score = when {
                oneRadioButton.isChecked -> 1
                twoRadioButton.isChecked -> 2
                threeRadioButton.isChecked -> 3
                fourRadioButton.isChecked -> 4
                else -> 5
            }

            //newBookViewModel.saveBook(nameBook, author, pages, resume, genre, score, publicationDate)
            newBookViewModel.saveBookInServer(nameBook, author, pages, resume, genre, score, publicationDate)
        }
    }

    private fun onMsgDoneSubscribe(msg: String?) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}