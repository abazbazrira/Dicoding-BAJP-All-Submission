package id.bazrira.submission3.data.repository.catalogue

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.network.Resource
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity

interface CatalogueRepository {

  fun getNowPlayingMovies(): LiveData<Resource<PagedList<ItemData>>>
  fun getOnTheAirTvShows(): LiveData<Resource<PagedList<ItemData>>>
  fun getDetailItem(movieId: Int?): LiveData<ItemData>
  fun getListFavoriteItems(type: String): LiveData<PagedList<ItemData>>
  fun setFavoriteItem(itemData: ItemEntity)
}