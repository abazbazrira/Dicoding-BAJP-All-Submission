package id.bazrira.submission1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.bazrira.submission1.databinding.ActivityHomeBinding
import id.bazrira.submission1.ui.adapter.SectionPagerAdapter
import id.bazrira.submission1.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private lateinit var viewModel: HomeViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this@HomeActivity).get(HomeViewModel::class.java)

    val sectionPagerAdapter = SectionPagerAdapter(this@HomeActivity, supportFragmentManager)
    binding.viewPager.adapter = sectionPagerAdapter
    binding.tabLayout.setupWithViewPager(binding.viewPager)

    supportActionBar?.elevation = 0f
  }
}