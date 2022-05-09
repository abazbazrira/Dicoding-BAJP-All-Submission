package id.bazrira.submission2.di

import id.bazrira.submission2.data.repository.catalogue.CatalogueRepositoryImpl
import id.bazrira.submission2.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSource

object Injection {

  fun provideCatalogRepository(): CatalogueRepositoryImpl {
    val remoteDataSource = CatalogueRemoteDataSource.getInstance()
    return CatalogueRepositoryImpl.getInstance(remoteDataSource)
  }
}