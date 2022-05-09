package id.bazrira.submission3.data.repository.catalogue.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity

interface CatalogueLocalDataSource {

  fun getListItems(type: String): DataSource.Factory<Int, ItemData>
  fun getListFavoriteItems(type: String): DataSource.Factory<Int, ItemData>
  fun getDetailItem(id: Int?): LiveData<ItemData>
  fun insertItems(items: List<ItemEntity>)
  fun setFavoriteItems(item: ItemEntity)
}