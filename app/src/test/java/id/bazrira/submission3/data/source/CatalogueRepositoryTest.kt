package id.bazrira.submission3.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import id.bazrira.submission3.LiveDataTestUtil
import id.bazrira.submission3.PagedListUtil
import id.bazrira.submission3.abstraction.network.Resource
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.Constants.TV_SHOW
import id.bazrira.submission3.abstraction.utils.DataDummy
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.local.CatalogueLocalDataSourceImpl
import id.bazrira.submission3.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSourceImpl
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CatalogueRepositoryTest {

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  private val local = mock(CatalogueLocalDataSourceImpl::class.java)
  private val remote = mock(CatalogueRemoteDataSourceImpl::class.java)
  private val catalogRepository = FakeCatalogRepository(local, remote)

  private val listMovie = DataDummy.generateDataMovieDummy()
  private val listTvShow = DataDummy.generateDataTvShowDummy()
  private val movie = listMovie[0]
  private val tvShow = listTvShow[0]

  @Test
  fun getNowPlayingMovies() {
    val dataSourceFactory =
      mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ItemData>
    `when`(local.getListItems(MOVIE)).thenReturn(dataSourceFactory)
    catalogRepository.getNowPlayingMovies()

    val itemData =
      Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
    verify(local).getListItems(MOVIE)

    assertNotNull(itemData.data)
    assertEquals(listMovie.size.toLong(), itemData.data?.size?.toLong())
  }

  @Test
  fun getMovieDetail() {
    val dummyItem = MutableLiveData<ItemData>()
    dummyItem.value = movie
    `when`(local.getDetailItem(movie.id)).thenReturn(dummyItem)

    val data = LiveDataTestUtil.getValue(catalogRepository.getDetailItem(movie.id))
    verify(local).getDetailItem(movie.id)
    assertNotNull(data)
    assertEquals(movie.id, data.id)
  }

  @Test
  fun getTvShowOnTheAir() {
    val dataSourceFactory =
      mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ItemData>
    `when`(local.getListItems(TV_SHOW)).thenReturn(dataSourceFactory)
    catalogRepository.getOnTheAirTvShows()

    val itemData =
      Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
    verify(local).getListItems(TV_SHOW)

    assertNotNull(itemData.data)
    assertEquals(listMovie.size.toLong(), itemData.data?.size?.toLong())
  }

  @Test
  fun getTvShowDetail() {
    val dummyItem = MutableLiveData<ItemData>()
    dummyItem.value = tvShow
    `when`(local.getDetailItem(tvShow.id)).thenReturn(dummyItem)

    val data = LiveDataTestUtil.getValue(catalogRepository.getDetailItem(tvShow.id))
    verify(local).getDetailItem(tvShow.id)
    assertNotNull(data)
    assertEquals(tvShow.id, data.id)
  }

  @Test
  fun getListFavoriteMovies() {
    val dataSourceFactory =
      mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ItemData>
    `when`(local.getListFavoriteItems(MOVIE)).thenReturn(dataSourceFactory)
    catalogRepository.getListFavoriteItems(MOVIE)

    val itemData = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
    verify(local).getListFavoriteItems(MOVIE)
    assertNotNull(itemData.data)
    assertEquals(listMovie.size.toLong(), itemData.data?.size?.toLong())
  }

  @Test
  fun getListFavoriteTvShows() {
    val dataSourceFactory =
      mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ItemData>
    `when`(local.getListFavoriteItems(TV_SHOW)).thenReturn(dataSourceFactory)
    catalogRepository.getListFavoriteItems(TV_SHOW)

    val itemData = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovieDummy()))
    verify(local).getListFavoriteItems(TV_SHOW)
    assertNotNull(itemData.data)
    assertEquals(listMovie.size.toLong(), itemData.data?.size?.toLong())
  }
}