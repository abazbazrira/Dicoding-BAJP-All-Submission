package id.bazrira.submission2.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission2.utils.DataDummy
import id.bazrira.submission2.viewmodel.HomeViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

  private val dummyMovie = DataDummy.generateDataMovieDummy()
  private val dummyTvShow = DataDummy.generateDataTvShowDummy()

  private lateinit var viewModel: HomeViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var catalogRepository: CatalogueRepositoryImpl

  @Mock
  private lateinit var observer: Observer<List<ItemData>>

  @Before
  fun setUp() {
    viewModel = HomeViewModel(catalogRepository)
  }

  @Test
  fun getListNowPlayingMovies() {
    val movie = MutableLiveData<List<ItemData>>()
    movie.value = dummyMovie

    `when`(catalogRepository.getNowPlayingMovies()).thenReturn(movie)

    val dataListMovie = viewModel.getListMovie().value

    verify(catalogRepository).getNowPlayingMovies()
    assertNotNull(dataListMovie)
    assertEquals(10, dataListMovie?.size)

    viewModel.getListMovie().observeForever(observer)
    verify(observer).onChanged(dummyMovie)
  }

  @Test
  fun getListOnTheAirTvShows() {
    val tvShow = MutableLiveData<List<ItemData>>()
    tvShow.value = dummyTvShow

    `when`(catalogRepository.getTvShowOnTheAir()).thenReturn(tvShow)

    val dataListTvShow = viewModel.getListTvShow().value

    verify(catalogRepository).getTvShowOnTheAir()
    assertNotNull(dataListTvShow)
    assertEquals(10, dataListTvShow?.size)

    viewModel.getListTvShow().observeForever(observer)
    verify(observer).onChanged(dummyTvShow)
  }
}