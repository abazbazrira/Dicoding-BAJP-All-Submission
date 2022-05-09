package id.bazrira.submission2.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.bazrira.submission2.R
import id.bazrira.submission2.utils.DataDummy
import id.bazrira.submission2.utils.EspressoIdlingResource.espressoTestIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

  private val dummyDataMovie = DataDummy.generateDataMovieDummyResponse()
  private val dummyDataTvShow = DataDummy.generateDataTvShowDummyResponse()

  @get:Rule
  var activityRule = ActivityScenarioRule(HomeActivity::class.java)

  @Before
  fun setUp() {
    IdlingRegistry.getInstance().register(espressoTestIdlingResource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(espressoTestIdlingResource)
  }

  @Test
  fun swipePage() {
    onView(withId(R.id.viewPager))
      .check(matches(isDisplayed()))
    onView(withId(R.id.viewPager))
      .perform(swipeLeft())
    onView(withId(R.id.viewPager))
      .perform(swipeRight())
  }

  @Test
  fun checkTabLayoutDisplayed() {
    onView(withId(R.id.tabLayout))
      .perform(click())
      .check(matches(isDisplayed()))
  }

  @Test
  fun loadData() {
    onView(withId(R.id.recyclerViewMovies)).check(matches(isDisplayed()))
    onView(withId(R.id.recyclerViewMovies)).perform(
      RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
        dummyDataMovie.size
      )
    )

    onView(allOf(withText("TV SHOWS"), isDescendantOfA(withId(R.id.tabLayout))))
      .perform(click())
      .check(matches(isDisplayed()))

    onView(withId(R.id.recyclerViewTvShows)).check(matches(isDisplayed()))
    onView(withId(R.id.recyclerViewTvShows)).perform(
      RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
        dummyDataTvShow.size
      )
    )

    onView(allOf(withText("MOVIES"), isDescendantOfA(withId(R.id.tabLayout))))
      .perform(click())
      .check(matches(isDisplayed()))
  }

  @Test
  fun detailMovie() {
    onView(withId(R.id.recyclerViewMovies)).check(matches(isDisplayed()))
    onView(withId(R.id.recyclerViewMovies)).perform(
      RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
        dummyDataMovie.size - 2,
      )
    )
    onView(withId(R.id.recyclerViewMovies)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        dummyDataTvShow.size - 2,
        click()
      )
    )

    onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()))
    onView(withId(R.id.textViewDescription)).check(matches(isDisplayed()))

    pressBack()
  }

  @Test
  fun detailTvShow() {
    onView(allOf(withText("TV SHOWS"), isDescendantOfA(withId(R.id.tabLayout))))
      .perform(click())
      .check(matches(isDisplayed()))

    onView(withId(R.id.recyclerViewTvShows)).check(matches(isDisplayed()))
    onView(withId(R.id.recyclerViewTvShows)).perform(
      RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
        dummyDataMovie.size - 2,
      )
    )
    onView(withId(R.id.recyclerViewTvShows)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        dummyDataTvShow.size - 2,
        click()
      )
    )

    onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()))
    onView(withId(R.id.textViewDescription)).check(matches(isDisplayed()))

    pressBack()
  }
}