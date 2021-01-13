package com.taytech.uitesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class SecondActivityTest {

    //rules to apply across all test cases
    //this rule states to create secondActivity class before each test
    @get: Rule
    val activityRule = ActivityScenarioRule(SecondActivity::class.java)

    //test to check if the second activity is displayed
    @Test
    fun test_isActivityDisplayed() {

        onView(withId(R.id.activity_second))
                .check(matches(isDisplayed()))
    }

    //test to check if the title is being displayed
    @Test
    fun test_visibilityTitle() {

        onView(withId(R.id.text_view_second_title))
                .check(matches(isDisplayed()))
    }

    //test to check if the back button is being displayed
    @Test
    fun test_visibilityBackButton() {

        //alternative way to check visibility
        onView(withId(R.id.button_back))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    //test to check if the title says "Second Activity"
    @Test
    fun test_isTitleTextCorrect(){

        onView(withId(R.id.text_view_second_title))
                .check(matches(withText(R.string.text_secondary_activity)))
    }
}