package id.bazrira.submission3.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.bazrira.submission3.R
import id.bazrira.submission3.abstraction.utils.Constants.REMOVED_FROM_FAVORITE
import id.bazrira.submission3.abstraction.utils.DataDummy
import id.bazrira.submission3.abstraction.utils.EspressoIdlingResource
import id.bazrira.submission3.features.home.ui.activity.HomeActivity
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

  private val dummyDataMovie = DataDummy.generateDataMovieDummy()
  private val dummyDataTvShow = DataDummy.generateDataTvShowDummy()

  @get:Rule
  var activityRule = ActivityScenarioRule(HomeActivity::class.java)

  @Before
  fun setUp() {
    IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
  }

  @Test
  fun testAllFeatures() {
    /**
     * Test Home Activity */
    onView(withId(R.id.nav_view))
      .check(matches(isDisplayed()))

    onView(withId(R.id.navigation_tv_show)).perform(click())
    onView(withId(R.id.navigation_favorite)).perform(click())

    onView(withId(R.id.viewPager))
      .check(matches(isDisplayed()))
    onView(withId(R.id.viewPager))
      .perform(swipeLeft())
    onView(withId(R.id.viewPager))
      .perform(swipeRight())

    onView(withId(R.id.navigation_movie)).perform(click())

    /**
     * Test Movie Features */
    onView(withId(R.id.recyclerViewItems))
      .check(matches(isDisplayed()))
      .perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
          2
        )
      )
      .perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
          2,
          click()
        )
      )

    onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()))
    onView(withId(R.id.textViewDescription)).check(matches(isDisplayed()))

    /**
     * Test Add Favorite Movie */
    onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
      .perform(click())

    onView(withContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description))
      .perform(click())

    /**
     * Test Tv Show Features */
    onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed())).perform(click())

    onView(withId(R.id.recyclerViewItems))
      .check(matches(isDisplayed()))
      .perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
          dummyDataTvShow.size
        )
      ).perform(
        RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
          2
        )
      ).perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
          2,
          click()
        )
      )

    onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()))
    onView(withId(R.id.textViewDescription)).check(matches(isDisplayed()))

    /**
     * Test Add Favorite Tv Show */
    onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
      .perform(click())

    onView(withContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description))
      .perform(click())

    /**
     * Test Remove Favorite Movie */
    onView(withId(R.id.navigation_favorite)).perform(click())

    onView(allOf(withText("MOVIES"), isDescendantOfA(withId(R.id.tabLayout))))
      .perform(click())
      .check(matches(isDisplayed()))

    onView(withId(R.id.recyclerViewFavoriteMovies))
      .check(matches(isDisplayed()))
      .perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
          0,
          click()
        )
      )

    onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
      .perform(click())

    onView(withId(com.google.android.material.R.id.snackbar_text))
      .check(matches(withText(REMOVED_FROM_FAVORITE)))

    onView(withContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description))
      .perform(click())

    /**
     * Test Remove Favorite Tv Show */
    onView(withId(R.id.navigation_favorite)).perform(click())

    onView(withId(R.id.viewPager))
      .check(matches(isDisplayed()))
    onView(withId(R.id.viewPager))
      .perform(swipeLeft())

    onView(allOf(withText("TV SHOWS"), isDescendantOfA(withId(R.id.tabLayout))))
      .perform(click())
      .check(matches(isDisplayed()))

    onView(withId(R.id.recyclerViewFavoriteTvShows))
      .check(matches(isDisplayed()))
      .perform(
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
          0,
          click()
        )
      )

    onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
      .perform(click())

    onView(withId(com.google.android.material.R.id.snackbar_text))
      .check(matches(withText(REMOVED_FROM_FAVORITE)))
  }
}