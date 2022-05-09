package id.bazrira.submission3.di.module

import dagger.Module
import dagger.Provides
import id.bazrira.submission3.network.Network
import id.bazrira.submission3.network.Services
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

  companion object {
    @Provides
    @Singleton
    fun provideService(): Services = Network.builder().create()
  }
}