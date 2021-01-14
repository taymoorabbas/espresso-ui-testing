package com.taytech.uitesting

import android.widget.Toast
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.taytech.uitesting.MainActivity.Companion.buildToastMessage
import org.hamcrest.CoreMatchers.not
import org.junit.Test

class MainActivityTest {

    //test to check the popup dialog is showing and it captures the name entered by user
    @Test
    fun test_showDialog_captureNameInput() {

        //launching the main activity
        ActivityScenario.launch(MainActivity::class.java)

        //test data to be entered in input dialog
        val expectedName = "Badshah Sarkar"

        //simulate a open input dialog button
        onView(withId(R.id.button_launch_dialog))
                .perform(click())

        //checking if the popup dialog is shown
        onView(withText(R.string.text_enter_name))
                .check(matches(isDisplayed()))

        //simulating a ok button click on empty input
        //the dialog should not close if the input is empty
        onView(withText(R.string.text_ok))
                .perform(click())

        //checking if dialog is still visible or not
        //(after clicking ok on a empty input)
        onView(withText(R.string.text_enter_name))
                .check(matches(isDisplayed()))

        //entering name in the input dialog
        //('md_input_message' is default id of material input)
        onView(withId(R.id.md_input_message))
                .perform(typeText(expectedName))

        //simulating the ok button click with input text filled
        onView(withText(R.string.text_ok))
                .perform(click())

        //checking if the popup has been closed
        onView(withText(R.string.text_enter_name))
                .check(doesNotExist())

        //checking if the entered name is displayed on text view
        onView(withId(R.id.text_name))
                .check(matches(withText(expectedName)))

        //checking if toast message is displayed
        //buildToastMessage = 'Your name is <expectedName>'
        //inRoot selects the window in which we want to check. in this case the window is of TOAST
        onView(withText(buildToastMessage(expectedName)))
                .inRoot(ToastMatcher())
                .check(matches(isDisplayed()))
    }

}