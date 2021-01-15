package com.taytech.uitesting.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.taytech.uitesting.R
import com.taytech.uitesting.data.FakeMovieData
import com.taytech.uitesting.ui.movie.MoviesListAdapter.*
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    //launch main activity before every test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //test data
    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]


    /*
   * RecyclerView comes into view
   * */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()))
    }

    /*
    * Select list item, nav to movie detail fragment,
    * correct movie is in view ?
    * */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
                .check(matches(withText(MOVIE_IN_TEST.title)))
    }

    /*
    * Select list item, nav to movie detail fragment,
    * press back (back to movie list fragment)
    * */
    @Test
    fun test_backNavigation_toMovieListFragment() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
                .check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()))
    }

    /*
    * Select list item, nav to movie detail fragment,
    * click on directors textView, goto directors fragment
    * */
    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
                .check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_directiors))
                .perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.directors_text))
                .check(matches(withText(
                        DirectorsFragment.stringBuilderForDirectors(MOVIE_IN_TEST.directors!!)
                )))
    }

    /*
   * Select list item, nav to movie detail fragment,
   * click on actors textView, goto actors fragment
   * */
    @Test
    fun test_navStarActorsFragment_validateActorsList() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title))
                .check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_star_actors))
                .perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.star_actors_text))
                .check(matches(withText(
                        StarActorsFragment.stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!)
                )))
    }
}









