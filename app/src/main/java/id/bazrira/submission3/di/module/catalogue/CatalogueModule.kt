package id.bazrira.submission3.di.module.catalogue

import dagger.Module
import dagger.Provides
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission3.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission3.data.repository.catalogue.local.CatalogueLocalDataSource
import id.bazrira.submission3.data.repository.catalogue.local.CatalogueLocalDataSourceImpl
import id.bazrira.submission3.data.repository.catalogue.local.dao.CatalogueDao
import id.bazrira.submission3.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSource
import id.bazrira.submission3.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSourceImpl
import id.bazrira.submission3.network.Services
import javax.inject.Singleton

@Module
class CatalogueModule {

  companion object {

    @Singleton
    @Provides
    fun provideCatalogueLocalDataSource(
      dao: CatalogueDao
    ): CatalogueLocalDataSource {
      return CatalogueLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideCatalogueRemoteDataSource(
      services: Services
    ): CatalogueRemoteDataSource {
      return CatalogueRemoteDataSourceImpl(services)
    }

    @Singleton
    @Provides
    fun provideCatalogueRepository(
      localDataSource: CatalogueLocalDataSource,
      remoteDataSource: CatalogueRemoteDataSource
    ): CatalogueRepository {
      return CatalogueRepositoryImpl(localDataSource, remoteDataSource)
    }
  }
}