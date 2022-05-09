package id.bazrira.submission1.viewmodel

import id.bazrira.submission1.utils.Constants.MOVIE
import id.bazrira.submission1.utils.Constants.TV_SHOW
import id.bazrira.submission1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

  private lateinit var viewModel: DetailViewModel
  private val dummyDataMovie = DataDummy.generateDataMovieDummy().first()
  private val dummyDataTvShow = DataDummy.generateDataTvShowDummy().first()
  private val movieId = dummyDataMovie.id
  private val tvShowId = dummyDataTvShow.id

  @Before
  fun setUp() {
    viewModel = DetailViewModel()
  }

  @Test
  fun getDetailDataMovie() {
    val data = viewModel.getDetailData(movieId, MOVIE)
    assertNotNull(data)
    assertEquals(dummyDataMovie.title, data.title)
    assertEquals(dummyDataMovie.desc, data.desc)
    assertEquals(dummyDataMovie.poster, data.poster)
    assertEquals(dummyDataMovie.backDrop, data.backDrop)
  }

  @Test
  fun getDetailDataTvShow() {
    val data = viewModel.getDetailData(tvShowId, TV_SHOW)
    assertNotNull(data)
    assertEquals(dummyDataTvShow.title, data.title)
    assertEquals(dummyDataTvShow.desc, data.desc)
    assertEquals(dummyDataTvShow.poster, data.poster)
    assertEquals(dummyDataTvShow.backDrop, data.backDrop)
  }
}