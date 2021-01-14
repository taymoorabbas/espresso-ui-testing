package com.taytech.uitesting.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.taytech.uitesting.R
import com.taytech.uitesting.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.fragment_directors.view.*
import org.junit.Test

class DirectorsFragmentTest{

    //test to check if the directors list view is visible
    @Test
    fun test_isDirectorsListVisible(){

        //data setup
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)

        //simulating directors fragment transaction
        launchFragmentInContainer<DirectorsFragment>(

                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        //check if correct list of directors is displayed
        onView(withId(R.id.directors_text))
                .check(matches(withText(

                DirectorsFragment.stringBuilderForDirectors(directors)
        )))
    }
}