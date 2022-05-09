package id.bazrira.submission3.features.home.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import id.bazrira.submission3.R
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.databinding.ActivityHomeBinding
import id.bazrira.submission3.features.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private lateinit var viewModel: HomeViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(
      this@HomeActivity,
      factory
    ).get(HomeViewModel::class.java)

    val navController = findNavController(R.id.nav_host_fragment)
    val appBarConfiguration = AppBarConfiguration.Builder(
      R.id.navigation_movie,
      R.id.navigation_tv_show,
      R.id.navigation_favorite,
    ).build()

    setupActionBarWithNavController(navController, appBarConfiguration)
    binding.navView.setupWithNavController(navController)

    supportActionBar?.elevation = 0f
  }
}