package id.bazrira.submission3.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.network.Resource
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission3.features.home.viewmodel.HomeViewModel
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
class HomeViewModelTest {

  private lateinit var viewModel: HomeViewModel

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  private lateinit var catalogRepository: CatalogueRepositoryImpl

  @Mock
  private lateinit var observerItem: Observer<Resource<PagedList<ItemData>>>

  @Mock
  private lateinit var itemPagedList: PagedList<ItemData>

  @Before
  fun setUp() {
    viewModel = HomeViewModel(catalogRepository)
  }

  @Test
  fun getListNowPlayingMovies() {
    val dummyItem = Resource.success(itemPagedList)
    `when`(dummyItem.data?.size).thenReturn(5)
    val item = MutableLiveData<Resource<PagedList<ItemData>>>()
    item.value = dummyItem

    `when`(catalogRepository.getNowPlayingMovies()).thenReturn(item)
    val itemEntity = viewModel.getListMovie().value?.data
    verify(catalogRepository).getNowPlayingMovies()
    assertNotNull(itemEntity)
    assertEquals(5, itemEntity?.size)

    viewModel.getListMovie().observeForever(observerItem)
    verify(observerItem).onChanged(dummyItem)
  }

  @Test
  fun getListOnTheAirTvShows() {
    val dummyItem = Resource.success(itemPagedList)
    `when`(dummyItem.data?.size).thenReturn(5)
    val item = MutableLiveData<Resource<PagedList<ItemData>>>()
    item.value = dummyItem

    `when`(catalogRepository.getOnTheAirTvShows()).thenReturn(item)
    val itemEntity = viewModel.getListTvShow().value?.data
    verify(catalogRepository).getOnTheAirTvShows()
    assertNotNull(itemEntity)
    assertEquals(5, itemEntity?.size)

    viewModel.getListTvShow().observeForever(observerItem)
    verify(observerItem).onChanged(dummyItem)
  }
}