package id.bazrira.submission3.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.Constants.TV_SHOW
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission3.features.favorite.viewmodel.FavoriteViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

  private lateinit var viewModel: FavoriteViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var catalogRepository: CatalogueRepository

  @Mock
  private lateinit var observerItem: Observer<PagedList<ItemData>>


  @Mock
  private lateinit var itemPagedList: PagedList<ItemData>

  @Before
  fun setUp() {
    viewModel = FavoriteViewModel(catalogRepository)
  }

  @Test
  fun getListFavoriteMovie() {

    val dummyMovie = itemPagedList
    `when`(dummyMovie.size).thenReturn(5)
    val movie = MutableLiveData<PagedList<ItemData>>()
    movie.value = dummyMovie

    `when`(catalogRepository.getListFavoriteItems(MOVIE)).thenReturn(movie)
    val movieData = viewModel.getListFavoriteMovie().value
    verify(catalogRepository).getListFavoriteItems(MOVIE)
    assertNotNull(movieData)
    assertEquals(5, movieData?.size)

    viewModel.getListFavoriteMovie().observeForever(observerItem)
    verify(observerItem).onChanged(dummyMovie)

  }

  @Test
  fun getListFavoriteTvShow() {
    val dummyTvShow = itemPagedList
    `when`(dummyTvShow.size).thenReturn(5)
    val tvShow = MutableLiveData<PagedList<ItemData>>()
    tvShow.value = dummyTvShow

    `when`(catalogRepository.getListFavoriteItems(TV_SHOW)).thenReturn(tvShow)
    val tvShowData = viewModel.getListFavoriteTvShow().value
    verify(catalogRepository).getListFavoriteItems(TV_SHOW)
    assertNotNull(tvShowData)
    assertEquals(5, tvShowData?.size)

    viewModel.getListFavoriteTvShow().observeForever(observerItem)
    verify(observerItem).onChanged(dummyTvShow)
  }
}
