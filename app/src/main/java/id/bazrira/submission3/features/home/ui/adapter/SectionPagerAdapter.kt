package id.bazrira.submission3.features.home.ui.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.bazrira.submission3.R
import id.bazrira.submission3.features.favorite.ui.fragment.FavoriteMovieFragment
import id.bazrira.submission3.features.favorite.ui.fragment.FavoriteTvShowFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
  FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  companion object {
    @StringRes
    private val tabTitles = intArrayOf(R.string.movies, R.string.tv_shows)
  }

  override fun getItem(position: Int): Fragment {
    var fragment: Fragment? = null

    when (position) {
      0 -> fragment = FavoriteMovieFragment()
      1 -> fragment = FavoriteTvShowFragment()
      else -> Fragment()
    }

    return fragment as Fragment
  }

  override fun getCount(): Int {
    return tabTitles.size
  }

  @Nullable
  override fun getPageTitle(position: Int): CharSequence {
    return context.resources.getString(tabTitles[position])
  }
}