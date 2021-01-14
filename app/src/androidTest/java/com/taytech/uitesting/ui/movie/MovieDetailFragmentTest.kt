package com.taytech.uitesting.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.taytech.uitesting.R
import com.taytech.uitesting.data.DummyMovies.THE_RUNDOWN
import com.taytech.uitesting.factory.MovieFragmentFactory
import org.junit.Test

class MovieDetailFragmentTest {

    //test to check if correct movie data is displayed on MovieDetailFragment UI components
    @Test
    fun test_isMovieDataVisible() {

        //data setup
        val movie = THE_RUNDOWN
        val fragmentFactory = MovieFragmentFactory();
        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        //simulating the movie fragment transaction
        launchFragmentInContainer<MovieDetailFragment>(

                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        //check if movie title on text view matches the movie title
        onView(withId(R.id.movie_title))
                .check(matches(withText(movie.title)))

        //check if movie title on text view matches the movie title
        onView(withId(R.id.movie_description))
                .check(matches(withText(movie.description)))
    }
}