package id.bazrira.submission3.data.repository.catalogue

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.network.ApiResponse
import id.bazrira.submission3.abstraction.network.NetworkBoundResource
import id.bazrira.submission3.abstraction.network.Resource
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.Constants.TV_SHOW
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.local.CatalogueLocalDataSource
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity
import id.bazrira.submission3.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSource
import id.bazrira.submission3.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogueRepositoryImpl @Inject constructor(
  private val localDataSource: CatalogueLocalDataSource,
  private val remoteDataSource: CatalogueRemoteDataSource,
) : CatalogueRepository {

  override fun getNowPlayingMovies(): LiveData<Resource<PagedList<ItemData>>> {
    return object : NetworkBoundResource<PagedList<ItemData>, List<MovieResponse>>() {
      public override fun loadFromDB(): LiveData<PagedList<ItemData>> {
        val config = PagedList.Config.Builder().apply {
          setEnablePlaceholders(false)
          setInitialLoadSizeHint(4)
          setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListItems(MOVIE), config).build()
      }

      override fun shouldFetch(data: PagedList<ItemData>?): Boolean =
        data == null || data.isEmpty()


      public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
        remoteDataSource.getNowPlayingMovies()

      public override fun saveCallResult(data: List<MovieResponse>) {
        val movieList = ArrayList<ItemEntity>()
        for (item in data) {
          val movie = ItemEntity(
            id = item.id,
            title = item.title.toString(),
            desc = item.desc.toString(),
            poster = item.poster.toString(),
            backDrop = item.backDrop.toString(),
            isFavorite = false,
            type = MOVIE
          )
          movieList.add(movie)
        }

        localDataSource.insertItems(movieList)
      }

    }.asLiveData()
  }

  override fun getOnTheAirTvShows(): LiveData<Resource<PagedList<ItemData>>> {
    return object : NetworkBoundResource<PagedList<ItemData>, List<TvShowResponse>>() {
      public override fun loadFromDB(): LiveData<PagedList<ItemData>> {
        val config = PagedList.Config.Builder().apply {
          setEnablePlaceholders(false)
          setInitialLoadSizeHint(4)
          setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListItems(TV_SHOW), config).build()
      }

      override fun shouldFetch(data: PagedList<ItemData>?): Boolean =
        data == null || data.isEmpty()


      public override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
        remoteDataSource.getOnTheAirTvShows()

      public override fun saveCallResult(data: List<TvShowResponse>) {
        val movieList = ArrayList<ItemEntity>()
        for (item in data) {
          val movie = ItemEntity(
            id = item.id,
            title = item.title.toString(),
            desc = item.desc.toString(),
            poster = item.poster.toString(),
            backDrop = item.backDrop.toString(),
            isFavorite = false,
            type = TV_SHOW
          )
          movieList.add(movie)
        }

        localDataSource.insertItems(movieList)
      }

    }.asLiveData()
  }

  override fun getDetailItem(movieId: Int?): LiveData<ItemData> {
    return localDataSource.getDetailItem(movieId)
  }

  override fun getListFavoriteItems(type: String): LiveData<PagedList<ItemData>> {
    val config = PagedList.Config.Builder().apply {
      setEnablePlaceholders(false)
      setInitialLoadSizeHint(4)
      setPageSize(4)
    }.build()
    return LivePagedListBuilder(localDataSource.getListFavoriteItems(type), config).build()
  }

  override fun setFavoriteItem(itemData: ItemEntity) {
    CoroutineScope(Dispatchers.IO).launch {
      localDataSource.setFavoriteItems(itemData)
    }
  }
}