package id.bazrira.submission3.di.module.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity
import id.bazrira.submission3.features.home.ui.activity.HomeActivity

@Module
abstract class ActivityBuildersModule {

  @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
  abstract fun contributeHomeActivity(): HomeActivity

  @ContributesAndroidInjector
  abstract fun contributeDetailActivity(): DetailActivity
}