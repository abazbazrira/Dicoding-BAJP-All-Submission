package id.bazrira.submission3.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.data.database.CatalogueDatabase
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission3.data.repository.catalogue.local.dao.CatalogueDao
import javax.inject.Singleton

@Module
class AppModule {

  companion object {
    @Singleton
    @Provides
    fun provideCatalogDatabase(application: Application): CatalogueDatabase =
      CatalogueDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideCatalogDao(catalogDatabase: CatalogueDatabase): CatalogueDao =
      catalogDatabase.catalogDao()

    @Singleton
    @Provides
    fun provideViewModelFactory(catalogRepository: CatalogueRepository): ViewModelFactory =
      ViewModelFactory(catalogRepository)
  }
}