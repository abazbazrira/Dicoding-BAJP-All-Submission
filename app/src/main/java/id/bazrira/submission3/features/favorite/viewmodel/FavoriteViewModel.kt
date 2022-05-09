package id.bazrira.submission3.features.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.Constants.TV_SHOW
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
  private val repository: CatalogueRepository
) : ViewModel() {

  fun getListFavoriteMovie(): LiveData<PagedList<ItemData>> =
    repository.getListFavoriteItems(MOVIE)

  fun getListFavoriteTvShow(): LiveData<PagedList<ItemData>> =
    repository.getListFavoriteItems(TV_SHOW)
}