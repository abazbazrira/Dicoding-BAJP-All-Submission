package id.bazrira.submission2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission2.di.Injection

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogueRepositoryImpl) :
  ViewModelProvider.NewInstanceFactory() {

  companion object {
    @Volatile
    private var instance: ViewModelFactory? = null

    fun getInstance(): ViewModelFactory =
      instance ?: synchronized(this) {
        instance ?: ViewModelFactory(Injection.provideCatalogRepository())
      }
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when {
      modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
        HomeViewModel(mCatalogRepository) as T
      }
      modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
        DetailViewModel(mCatalogRepository) as T
      }
      else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
    }

  }
}