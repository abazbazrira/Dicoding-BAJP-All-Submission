package id.bazrira.submission3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.bazrira.submission3.data.repository.catalogue.local.dao.CatalogueDao
import id.bazrira.submission3.data.repository.catalogue.local.entity.ItemEntity

@Database(
  entities = [
    ItemEntity::class,
  ], version = 1, exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {
  abstract fun catalogDao(): CatalogueDao

  companion object {

    @Volatile
    private var INSTANCE: CatalogueDatabase? = null

    fun getInstance(context: Context): CatalogueDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: Room.databaseBuilder(
          context.applicationContext,
          CatalogueDatabase::class.java,
          "catalogue.db"
        ).build()
      }
  }
}