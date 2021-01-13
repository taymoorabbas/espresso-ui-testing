package com.taytech.uitesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test

//MainActivity test class
class MainActivityTest {

    //these tests are run in alphabetical order

    //test to check if the activity is launched and displayed successfully
    @Test
    fun test_isActivityInView() {

        //launch function launches the activity
        ActivityScenario.launch(MainActivity::class.java)

        //checking if the main activity is being displayed
        onView(withId(R.id.activity_main))
                .check(matches(isDisplayed()))
    }

    //test to check if the title of the main activity is being displayed
    @Test
    fun test_visibilityTitle() {

        //launching the main activity
        ActivityScenario.launch(MainActivity::class.java)

        //checking if main title is being displayed
        onView(withId(R.id.text_view_main_title))
                .check(matches(isDisplayed()))
    }

    //test to check if the next button is being displayed
    @Test
    fun test_visibilityNextButton() {

        //launching the main activity
        ActivityScenario.launch(MainActivity::class.java)

        //checking
        onView(withId(R.id.button_next_activity))
                .check(matches(isDisplayed()))
    }

    //test to check if the main title says "Main Activity"
    @Test
    fun test_isTitleTextCorrect() {

        //launching the main activity
        ActivityScenario.launch(MainActivity::class.java)

        //checking the title text
        onView(withId(R.id.text_view_main_title))
                .check(matches(withText(R.string.text_main_activity)))
    }
}