package id.bazrira.submission3.features.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(
  private val repository: CatalogueRepository
) : ViewModel() {

  fun getDetailData(id: Int?): LiveData<ItemData> {
    return repository.getDetailItem(id)
  }

  fun setFavoriteItem(item: ItemEntity) {
    repository.setFavoriteItem(item)
  }
}