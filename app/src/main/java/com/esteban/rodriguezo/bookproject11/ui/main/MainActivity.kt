package com.esteban.rodriguezo.bookproject11.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.esteban.rodriguezo.bookproject11.R
import com.esteban.rodriguezo.bookproject11.databinding.ActivityMainBinding
import com.esteban.rodriguezo.bookproject11.ui.login.LogingActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var cal = Calendar.getInstance()
    private var publicationDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        Log.d("state", "onCreate")

        //val nameBookEditText: EditText = findViewById(R.id.name_book_edit_text)
/*
        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            mainBinding.publicationDateButton.text = publicationDate

        }

        with(mainBinding) {

            publicationDateButton.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {

                if (nameBookEditText.text?.isEmpty() == true ||
                    nameAuthorEditText.text?.isEmpty() ==true ||
                    pagesEditText.text?.isEmpty() == true
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Debe digitar nombre, autor y numero de paginas",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    val nameBook: String = nameBookEditText.text.toString()
                    val author = nameAuthorEditText.text.toString()
                    val pages = pagesEditText.text.toString().toInt()
                    val abstract = abstractEditText.text.toString()

                    var genre = ""

                    if (susteneCheckBox.isChecked) genre += "Suspenso"
                    if (fictionCheckBox.isChecked) genre += "Ficcion"
                    if (horrorCheckBox.isChecked) genre += "Terror"
                    if (childishCheckBox.isChecked) genre += "Infantil"

                    //var score = if(oneRadioButton.isChecked) 1 else 2
                    val score = when {
                        oneRadioButton.isChecked -> 1
                        twoRadioButton.isChecked -> 2
                        threeRadioButton.isChecked -> 3
                        fourRadioButton.isChecked -> 4
                        else -> 5
                    }

                    infoTextView.text = getString(R.string.info, nameBook, author, pages, abstract, genre ,score ,publicationDate)

                }

            }
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sign_out -> goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LogingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

   /* override fun onStart() {
        super.onStart()
        Log.d("state", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("state", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("state", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("state", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("state", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("state", "onRestart")
    }*/

}
