package id.bazrira.submission1.ui.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.bazrira.submission1.R
import id.bazrira.submission1.ui.fragment.MovieFragment
import id.bazrira.submission1.ui.fragment.TvShowFragment

class SectionPagerAdapter(private val context: Context, fragmenrManager: FragmentManager) : FragmentPagerAdapter(fragmenrManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  @StringRes
  private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)

  override fun getItem(position: Int): Fragment {
    var fragment: Fragment? = null

    when (position) {
      0 -> fragment = MovieFragment()
      1 -> fragment = TvShowFragment()
    }

    return fragment as Fragment
  }

  override fun getCount(): Int {
    return 2
  }

  @Nullable
  override fun getPageTitle(position: Int): CharSequence {
    return context.resources.getString(TAB_TITLES[position])
  }
}