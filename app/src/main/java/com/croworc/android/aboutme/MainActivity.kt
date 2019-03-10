package com.croworc.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.croworc.android.aboutme.data.MyName
import com.croworc.android.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName = MyName("Nick van Heussen")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName // Assign the MyName class instance to the binding class's myName variable

        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(doneButton: View) {

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString() /* We're assigning the nickname the user has entered in the
                                                               EditText to the respective property of our data class
                                                               instance. As this object is bound to the layout, the
                                                               nickname will then *automatically* be shown in
                                                               the TextView.
                                                            */
            invalidateAll()                                 // Re-draw the views, so that the new data is shown
            nicknameEdit.visibility = View.GONE             // Hide the EditText
            doneButton.visibility = View.GONE               // Hide the DONE button
            nicknameText.visibility = View.VISIBLE          // Show the nickname TextView
        }

        // Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(doneButton.windowToken, 0)
    }
}
