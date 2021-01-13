package com.taytech.uitesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

//MainActivity test class
class MainActivityTest {

    //test to check button click navigates to second activity
    @Test
    fun test_buttonNavigatingToSecondActivity() {

        //simulation of main activity launch (sand boxed)
        ActivityScenario.launch(MainActivity::class.java)

        //checking if mainActivity is displayed
        onView(withId(R.id.activity_main))
                .check(matches(isDisplayed()))

        //triggering a nextButton click
        onView(withId(R.id.button_next_activity))
                .perform(click())

        //now checking if the second activity is displayed (as a result of button click)
        onView(withId(R.id.activity_second))
                .check(matches(isDisplayed()))
    }

    //test to check if back press navigates to main activity
    //notice the flow!
    @Test
    fun test_backPressedFromSecondActivity() {

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
        Espresso.pressBack()

        //checking if main activity is being displayed
        onView(withId(R.id.activity_main))
                .check(matches(isDisplayed()))
    }
}