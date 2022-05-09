package id.bazrira.submission3.di.module.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.bazrira.submission3.features.favorite.ui.fragment.FavoriteFragment
import id.bazrira.submission3.features.home.ui.fragment.MovieFragment
import id.bazrira.submission3.features.home.ui.fragment.TvShowFragment

@Module
abstract class HomeFragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeMovieFragment(): MovieFragment

  @ContributesAndroidInjector
  abstract fun contributeTvShowFragment(): TvShowFragment

  @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
  abstract fun contributeFavoriteFragment(): FavoriteFragment
}