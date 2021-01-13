package com.taytech.uitesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class SecondActivityTest {

    //test to check if button click navigates to main activity
    @Test
    fun test_backButtonNavigatingToMainActivity() {

        //simulating a main activity launch
        ActivityScenario.launch(MainActivity::class.java)

        //checking if mainActivity is displayed (optional)
        onView(withId(R.id.activity_main))
                .check(matches(isDisplayed()))

        //simulating a button click to goto secondActivity
        onView(withId(R.id.button_next_activity))
                .perform(click())

        //checking if the second activity is displayed (optional)
        onView(withId(R.id.activity_second))
                .check(matches(isDisplayed()))

        //simulating a back press
        onView(withId(R.id.button_back))
                .perform(click())

        //checking if main activity is being displayed
        onView(withId(R.id.activity_main))
                .check(matches(isDisplayed()))
    }
}