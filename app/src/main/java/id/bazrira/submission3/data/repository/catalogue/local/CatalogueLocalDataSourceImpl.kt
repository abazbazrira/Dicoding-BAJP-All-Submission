package id.bazrira.submission3.data.repository.catalogue.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.DataSource
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.data.repository.catalogue.local.dao.CatalogueDao
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity
import javax.inject.Inject

class CatalogueLocalDataSourceImpl @Inject constructor(
  private val dao: CatalogueDao
) : CatalogueLocalDataSource {

  override fun getListItems(type: String): DataSource.Factory<Int, ItemData> {
    return dao.getListItems(type)
      .map { itemMap ->
        ItemData(
          id = itemMap.id,
          title = itemMap.title,
          poster = itemMap.poster,
          backDrop = itemMap.backDrop,
          desc = itemMap.desc,
          isFavorite = itemMap.isFavorite,
        )
      }
  }

  override fun getListFavoriteItems(type: String): DataSource.Factory<Int, ItemData> {
    return dao.getListFavoriteItems(type)
      .map { itemMap ->
        ItemData(
          id = itemMap.id,
          title = itemMap.title,
          poster = itemMap.poster,
          backDrop = itemMap.backDrop,
          desc = itemMap.desc,
          isFavorite = itemMap.isFavorite,
        )
      }
  }

  override fun getDetailItem(id: Int?): LiveData<ItemData> {
    return dao.getDetailItemById(id).map { itemMap ->
      ItemData(
        id = itemMap.id,
        title = itemMap.title,
        poster = itemMap.poster,
        backDrop = itemMap.backDrop,
        desc = itemMap.desc,
        isFavorite = itemMap.isFavorite,
      )
    }
  }

  override fun insertItems(items: List<ItemEntity>) {
    return dao.insertItems(items)
  }

  override fun setFavoriteItems(item: ItemEntity) {
    return dao.updateItem(item)
  }
}