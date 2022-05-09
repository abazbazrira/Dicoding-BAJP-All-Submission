package id.bazrira.submission1.viewmodel

import id.bazrira.submission1.viewmodel.HomeViewModel
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class HomeViewModelTest {

  private lateinit var viewModel: HomeViewModel

  @Before
  fun setUp() {
    viewModel = HomeViewModel()
  }

  @Test
  fun getListMovie() {
    val movies = viewModel.getListMovie()
    TestCase.assertNotNull(movies)
    assertEquals(10, movies.size)
  }

  @Test
  fun getListTvShow() {
    val tvShows = viewModel.getListTvShow()
    TestCase.assertNotNull(tvShows)
    assertEquals(10, tvShows.size)
  }
}