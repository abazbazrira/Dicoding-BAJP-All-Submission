package id.bazrira.submission2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl

class HomeViewModel(private val repository: CatalogueRepositoryImpl) : ViewModel() {

  fun getListMovie(): LiveData<List<ItemData>> = repository.getNowPlayingMovies()

  fun getListTvShow(): LiveData<List<ItemData>> = repository.getTvShowOnTheAir()
}