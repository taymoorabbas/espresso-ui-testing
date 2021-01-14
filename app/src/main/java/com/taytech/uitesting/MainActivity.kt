package com.taytech.uitesting

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import java.util.*

class MainActivity : Activity(), View.OnClickListener {

    private var mInputText: EditText? = null
    private var mSuccessView: View? = null
    private var mErrorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sets the listener for the button.
        findViewById<View>(R.id.button).setOnClickListener(this)

        // Get references to the EditText and views showing the result.
        mInputText = findViewById<View>(R.id.editText) as EditText
        mSuccessView = findViewById(R.id.inputValidationSuccess)
        mErrorView = findViewById(R.id.inputValidationError)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) {
            // The View to display depends on whether the input is valid or not.
            val inputText = mInputText!!.text.toString()

            // Validate the input and show the result.
            showResult(validateText(inputText))
        }
    }

    private fun showResult(isValidResult: Boolean) {
        mSuccessView!!.visibility = if (isValidResult) View.VISIBLE else View.GONE
        mErrorView!!.visibility = if (isValidResult) View.GONE else View.VISIBLE
    }

    companion object {
        @VisibleForTesting
        val COFFEE_PREPARATIONS = Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew")

        @VisibleForTesting
        val VALID_ENDING = "coffee"
        private fun validateText(inputText: String): Boolean {
            // Every input ending in VALID_ENDING will return true.
            if (inputText.toLowerCase().endsWith(VALID_ENDING)) {
                return true
            }

            // Check if the string is in the list.
            for (preparation in COFFEE_PREPARATIONS) {
                if (preparation.equals(inputText, ignoreCase = true)) {
                    return true
                }
            }
            return false
        }
    }
}
