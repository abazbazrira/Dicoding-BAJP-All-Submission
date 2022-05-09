package id.bazrira.submission2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission2.utils.Constants.MOVIE
import id.bazrira.submission2.utils.Constants.TV_SHOW
import id.bazrira.submission2.utils.DataDummy
import id.bazrira.submission2.viewmodel.DetailViewModel
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
class DetailViewModelTest {

  private val dummyMovie = DataDummy.generateDataMovieDummy()[0]
  private val movieId = dummyMovie.id
  private val dummyTvShow = DataDummy.generateDataTvShowDummy()[0]
  private val tvShowId = dummyTvShow.id

  private lateinit var viewModel: DetailViewModel

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var catalogRepository: CatalogueRepositoryImpl

  @Mock
  private lateinit var observer: Observer<ItemData>

  @Before
  fun setUp() {
    viewModel = DetailViewModel(catalogRepository)
  }

  @Test
  fun getMovieDetail() {
    val movieDummy = MutableLiveData<ItemData>()
    movieDummy.value = dummyMovie

    `when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

    val movieData = viewModel.getDetailData(movieId, MOVIE).value as ItemData

    assertNotNull(movieData)
    assertEquals(dummyMovie.id, movieData.id)
    assertEquals(dummyMovie.title, movieData.title)
    assertEquals(dummyMovie.desc, movieData.desc)
    assertEquals(dummyMovie.poster, movieData.poster)
    assertEquals(dummyMovie.backDrop, movieData.backDrop)

    viewModel.getDetailData(movieId, MOVIE).observeForever(observer)
    verify(observer).onChanged(dummyMovie)

  }

  @Test
  fun getTvShowDetail() {
    val tvShowDummy = MutableLiveData<ItemData>()
    tvShowDummy.value = dummyTvShow

    `when`(catalogRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

    val tvShowData = viewModel.getDetailData(tvShowId, TV_SHOW).value as ItemData

    assertNotNull(tvShowData)
    assertEquals(dummyTvShow.id, tvShowData.id)
    assertEquals(dummyTvShow.title, tvShowData.title)
    assertEquals(dummyTvShow.desc, tvShowData.desc)
    assertEquals(dummyTvShow.poster, tvShowData.poster)
    assertEquals(dummyTvShow.backDrop, tvShowData.backDrop)

    viewModel.getDetailData(tvShowId, TV_SHOW).observeForever(observer)
    verify(observer).onChanged(dummyTvShow)
  }
}