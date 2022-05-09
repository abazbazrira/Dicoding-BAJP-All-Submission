package id.bazrira.submission2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission2.utils.Constants.MOVIE

class DetailViewModel(private val repository: CatalogueRepositoryImpl) : ViewModel() {

  fun getDetailData(id: Int, type: String): LiveData<ItemData> {
    return if (type == MOVIE) {
      repository.getMovieDetail(id)
    } else {
      repository.getTvShowDetail(id)
    }
  }
}