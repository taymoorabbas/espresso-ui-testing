package com.taytech.uitesting.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.taytech.uitesting.R
import com.taytech.uitesting.factory.MovieFragmentFactory
import org.junit.Test

class StarActorsFragmentTest {

    //test to check if the actors list view is visible
    @Test
    fun test_isDirectorsListVisible() {

        //data setup
        val actors = arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", actors)

        //simulating actors fragment transaction
        launchFragmentInContainer<StarActorsFragment>(

                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        //check if correct list of actors is displayed
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_text))
                .check(ViewAssertions.matches(ViewMatchers.withText(

                        StarActorsFragment.stringBuilderForStarActors(actors)
                )))
    }
}