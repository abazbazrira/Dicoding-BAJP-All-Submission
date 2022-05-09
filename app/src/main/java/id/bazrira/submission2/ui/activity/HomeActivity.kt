package id.bazrira.submission2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission2.databinding.ActivityHomeBinding
import id.bazrira.submission2.ui.adapter.SectionPagerAdapter
import id.bazrira.submission2.viewmodel.HomeViewModel
import id.bazrira.submission2.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private lateinit var viewModel: HomeViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val factory = ViewModelFactory.getInstance()
    viewModel = ViewModelProvider(this@HomeActivity, factory).get(HomeViewModel::class.java)

    val sectionPagerAdapter = SectionPagerAdapter(this@HomeActivity, supportFragmentManager)
    binding.viewPager.adapter = sectionPagerAdapter
    binding.tabLayout.setupWithViewPager(binding.viewPager)

    supportActionBar?.elevation = 0f
  }
}