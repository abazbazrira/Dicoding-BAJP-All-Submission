package id.bazrira.submission3.data.repository.catalogue.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity

@Dao
interface CatalogueDao {

  @Query("SELECT * FROM items WHERE item_type = :itemType")
  fun getListItems(itemType: String): DataSource.Factory<Int, ItemEntity>

  @Query("SELECT * FROM items WHERE is_favorite = 1 and item_type = :itemType")
  fun getListFavoriteItems(itemType: String): DataSource.Factory<Int, ItemEntity>

  @Query("SELECT * FROM items WHERE id = :id")
  fun getDetailItemById(id: Int?): LiveData<ItemEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ItemEntity::class)
  fun insertItems(items: List<ItemEntity>)

  @Update(entity = ItemEntity::class)
  fun updateItem(item: ItemEntity)
}