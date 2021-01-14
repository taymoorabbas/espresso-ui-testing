package com.taytech.uitesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    // A valid string with a valid ending
    private var stringTypedEndingWithCoffee: String? = null

    // A valid string from the coffee preparations
    private var stringTypedWithValidName: String? = null

    private var stringTypeInvalid: String? = null

    @get: Rule
    val activityScenarioRule:
            ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    //initialize valid test data. this will run before any test case
    @Before
    fun initValidStrings() {

        // Produce a string with valid ending.
        //ie. Random coffee
        stringTypedEndingWithCoffee = "Random " + MainActivity.VALID_ENDING

        // Get one of the available coffee preparations.
        //ie. Espresso, Latte
        stringTypedWithValidName = MainActivity.COFFEE_PREPARATIONS[0]

        //invalid name
        //it can be anything. ie. Coca Cola
        stringTypeInvalid = "Doodh Soda"
    }

    //test to check the hint text is being shown in the edit text
    @Test
    fun hint_isDisplayedInEditText() {

        //getting the hint string from res
        val hintText = ApplicationProvider
                .getApplicationContext<Context>()
                .resources
                .getString(R.string.hint)

        //validating the hint is same as in the res
        Espresso.onView(withId(R.id.editText))
                .check(matches(withHint(hintText)))
    }

    //test to check if edit text is editable and then verifying the typed text
    @Test
    fun editText_canBeTypedInto() {

        //entering a valid coffee name in edit text,
        //closing the keyboard
        //and then verifying the text
        Espresso.onView(withId(R.id.editText))
                .perform(typeText(stringTypedWithValidName), closeSoftKeyboard())
                .check(matches(withText(stringTypedWithValidName)))
    }

    //test to check that correct message is displayed after entering a valid coffee name
    //ie. Latte
    @Test
    fun validation_resultIsOneOfTheValidStrings() {

        //typing a valid coffee name in edit text
        Espresso.onView(withId(R.id.editText))
                .perform(typeText(stringTypedWithValidName), closeSoftKeyboard())

        //clicking on validate button after typing the coffee name
        Espresso.onView(withId(R.id.button)).perform(click())

        //check that the correct sign is displayed
        Espresso.onView(withId(R.id.inputValidationSuccess))
                .check(matches(isDisplayed()))

        //check that the incorrect sign is not displayed
        Espresso.onView(withId(R.id.inputValidationError))
                .check(matches(not(isDisplayed())))
    }

    //test to check that correct message is displayed after
    //entering a random name ending with 'coffee'
    //ie. Hot coffee
    @Test
    fun validation_resultHasCorrectEnding() {

        //type any name ending with 'coffee' in edit text
        Espresso.onView(withId(R.id.editText))
                .perform(typeText(
                        stringTypedEndingWithCoffee),
                        closeSoftKeyboard())

        //perform the validate button click
        Espresso.onView(withId(R.id.button))
                .perform(click())

        //check that the correct sign is displayed
        Espresso.onView(withId(R.id.inputValidationSuccess))
                .check(matches(isDisplayed()))

        //check that the incorrect sign is not displayed
        Espresso.onView(withId(R.id.inputValidationError))
                .check(matches(not(isDisplayed())))
    }

    //test to check that incorrect message is displayed after entering a invalid coffee name
    //ie. Coca cola
    @Test
    fun validation_resultIsIncorrect() {

        //Type a invalid string in the edit text
        Espresso.onView(withId(R.id.editText))
                .perform(typeText(
                        stringTypeInvalid),
                        closeSoftKeyboard())

        //click on the validate button after typing
        Espresso.onView(withId(R.id.button))
                .perform(click())

        //Check that the correct sign is displayed
        Espresso.onView(withId(R.id.inputValidationError))
                .check(matches(isDisplayed()))

        //Check that the incorrect sign is not displayed
        Espresso.onView(withId(R.id.inputValidationSuccess))
                .check(matches(not(isDisplayed())))
    }
}