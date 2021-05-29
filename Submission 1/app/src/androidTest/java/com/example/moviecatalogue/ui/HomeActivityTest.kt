package com.example.moviecatalogue.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val movieList = DataDummy.generateDummyMovies()
    private val tvList = DataDummy.generateDummyTVs()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovieList() {
        onView(withId(R.id.rv_movies)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieList.size))
    }

    @Test
    fun loadMovieInfo() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_title_movie)).check(matches(withText(movieList[0].title)))
        onView(withId(R.id.txt_releasedate_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_releasedate_movie)).check(matches(withText(movieList[0].releaseDate)))
        onView(withId(R.id.txt_userscore_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_userscore_movie)).check(matches(withText(movieList[0].userScore)))
        onView(withId(R.id.txt_director)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_director)).check(matches(withText(movieList[0].director)))
        onView(withId(R.id.txt_overview_movie)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_overview_movie)).check(matches(withText(movieList[0].overview)))
    }

    @Test
    fun loadTVList() {
        onView(withId(R.id.navigation_tv)).perform(click()).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvs)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvList.size))
    }

    @Test
    fun loadTVInfo() {
        onView(withId(R.id.navigation_tv)).perform(click()).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvs)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title_tv)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_title_tv)).check(matches(withText(tvList[0].title)))
        onView(withId(R.id.txt_userscore_tv)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_userscore_tv)).check(matches(withText(tvList[0].userScore)))
        onView(withId(R.id.txt_creator)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_creator)).check(matches(withText(tvList[0].creator)))
        onView(withId(R.id.txt_overview_tv)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.txt_overview_tv)).check(matches(withText(tvList[0].overview)))
    }
}