package id.bazrira.submission3.features.favorite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.databinding.FragmentFavoriteBinding
import id.bazrira.submission3.features.favorite.viewmodel.FavoriteViewModel
import id.bazrira.submission3.features.home.ui.adapter.SectionPagerAdapter
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {

  private var _binding: FragmentFavoriteBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: FavoriteViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel = ViewModelProvider(this@FavoriteFragment, factory).get(FavoriteViewModel::class.java)

    val sectionPagerAdapter = SectionPagerAdapter(requireContext(), childFragmentManager)
    binding.viewPager.adapter = sectionPagerAdapter
    binding.tabLayout.setupWithViewPager(binding.viewPager)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}