package com.example.moviecatalogue.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieList() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
    }

    @Test
    fun loadMovieInfo() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_releasedate_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_userscore_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_popularity_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_overview_movie)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun loadTVList() {
        onView(withId(R.id.navigation_tv)).perform(click()).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadTVInfo() {
        onView(withId(R.id.navigation_tv)).perform(click()).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.image_poster_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_userscore_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_popularity_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_overview_tv)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun loadMovieFavoriteList() {
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText(R.string.title_movietab)).perform(click())
        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(DataDummy.generateDummyMovieList().size))
    }

    @Test
    fun loadMovieFavoriteInfo() {
        // load movie list
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
        // load movie
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // set as favorite
        onView((withId(R.id.navigation_favorite))).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText(R.string.title_movietab)).perform(click())
        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_releasedate_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_userscore_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_popularity_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_overview_movie)).check(matches(isDisplayed()))

        // set as unfavorite
        onView((withId(R.id.navigation_favorite))).perform(click())
        onView(isRoot()).perform(pressBack())

        pressBack()
    }

    @Test
    fun loadTVFavoriteList() {
        onView(withId(R.id.navigation_favorite)).perform(click()) //.check(matches(isDisplayed()))
        onView(withText(R.string.title_tv)).perform(click())
        onView(withId(R.id.rv_tvs_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs_favorite)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(DataDummy.generateDummyTVList().size))
    }

    @Test
    fun loadTVFavoriteInfo() {
        // load tv list
        onView(withId(R.id.navigation_tv)).perform(click()).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        // load tv
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // set as favorite
        onView((withId(R.id.navigation_favorite))).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.navigation_favorite)).perform(click()) //.check(matches(isDisplayed()))
        onView(withText(R.string.title_tv)).perform(click())
        onView(withId(R.id.rv_tvs_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvs_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_userscore_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_popularity_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_overview_tv)).check(matches(isDisplayed()))

        // set as unfavorite
        onView((withId(R.id.navigation_favorite))).perform(click())
        onView(isRoot()).perform(pressBack())

        pressBack()
    }
}