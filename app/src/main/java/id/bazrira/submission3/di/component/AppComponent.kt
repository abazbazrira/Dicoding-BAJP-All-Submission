package id.bazrira.submission3.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.bazrira.submission3.abstraction.base.BaseApplication
import id.bazrira.submission3.di.module.AppModule
import id.bazrira.submission3.di.module.NetworkModule
import id.bazrira.submission3.di.module.builder.ActivityBuildersModule
import id.bazrira.submission3.di.module.catalogue.CatalogueModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    CatalogueModule::class,
    NetworkModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}