package id.bazrira.submission1.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.bazrira.submission1.R
import id.bazrira.submission1.utils.DataDummy
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

  private val dummyDataMovie = DataDummy.generateDataMovieDummy()
  private val dummyDataTvShow = DataDummy.generateDataTvShowDummy()

  @get:Rule
  var activityRule = ActivityScenarioRule(HomeActivity::class.java)

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
        dummyDataMovie.size - 2
      )
    )
    onView(withId(R.id.recyclerViewMovies)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        dummyDataMovie.size - 2,
        click()
      )
    )

    onView(withId(R.id.textViewTitle)).check(matches(withText(dummyDataMovie[dummyDataMovie.size - 2].title)))
    onView(withId(R.id.textViewDescription)).check(matches(withText(dummyDataMovie[dummyDataMovie.size - 2].desc)))

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
        dummyDataTvShow.size - 3
      )
    )
    onView(withId(R.id.recyclerViewTvShows)).perform(
      RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        dummyDataTvShow.size - 2,
        click()
      )
    )

    onView(withId(R.id.textViewTitle)).check(matches(withText(dummyDataTvShow[dummyDataTvShow.size - 2].title)))
    onView(withId(R.id.textViewDescription)).check(matches(withText(dummyDataTvShow[dummyDataTvShow.size - 2].desc)))

    pressBack()
  }
}