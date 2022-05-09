package id.bazrira.submission3.di.module.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.bazrira.submission3.features.favorite.ui.fragment.FavoriteMovieFragment
import id.bazrira.submission3.features.favorite.ui.fragment.FavoriteTvShowFragment

@Module
abstract class FavoriteFragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeFavoriteMovieFragment(): FavoriteMovieFragment

  @ContributesAndroidInjector
  abstract fun contributeFavoriteTvShowFragment(): FavoriteTvShowFragment
}