package id.bazrira.submission3.abstraction.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission3.features.detail.viewmodel.DetailViewModel
import id.bazrira.submission3.features.favorite.viewmodel.FavoriteViewModel
import id.bazrira.submission3.features.home.viewmodel.HomeViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
  private val catalogueRepository: CatalogueRepository
) : ViewModelProvider.NewInstanceFactory() {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when {
      modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
        HomeViewModel(catalogueRepository) as T
      }
      modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
        DetailViewModel(catalogueRepository) as T
      }
      modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
        FavoriteViewModel(catalogueRepository) as T
      }
      else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
    }
  }
}