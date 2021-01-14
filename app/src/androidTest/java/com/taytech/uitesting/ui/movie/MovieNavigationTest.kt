package com.taytech.uitesting.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.taytech.uitesting.R
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.junit.Test

//class to test various navigation among movie fragments
class MovieNavigationTest {

    @Test
    fun test_movieFragmentsNavigation() {

        //simulating main activity launch
        //(as a result, fragment transaction to movieFragment will occur)
        ActivityScenario
                .launch(MainActivity::class.java)

        //simulate a directors text view click
        onView(withId(R.id.movie_directiors))
                .perform(click())

        //verify fragment transaction has occurred from movie fragment -> directors fragment
        onView(withId(R.id.fragment_directors))
                .check(matches(isDisplayed()))

        //simulate a go back to movie detail fragment
        pressBack()

        //verify that back navigation to movie detail has occurred
        onView(withId(R.id.fragment_movie_detail))
                .check(matches(isDisplayed()))

        //simulate a actors text view click
        onView(withId(R.id.movie_star_actors))
                .perform(click())

        //verify fragment transaction has occurred from movie fragment -> actors fragment
        onView(withId(R.id.fragment_actors))
                .check(matches(isDisplayed()))


    }
}