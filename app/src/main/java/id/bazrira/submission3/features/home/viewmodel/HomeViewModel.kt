package id.bazrira.submission3.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.bazrira.submission3.abstraction.network.Resource
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  private val repository: CatalogueRepository
) : ViewModel() {

  fun getListMovie(): LiveData<Resource<PagedList<ItemData>>> = repository.getNowPlayingMovies()
  fun getListTvShow(): LiveData<Resource<PagedList<ItemData>>> = repository.getOnTheAirTvShows()
}