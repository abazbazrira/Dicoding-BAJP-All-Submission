package id.bazrira.submission3.features.favorite.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import id.bazrira.submission3.R
import id.bazrira.submission3.abstraction.callback.ItemCallback
import id.bazrira.submission3.abstraction.utils.Constants.TV_SHOW
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.databinding.FragmentFavoriteTvShowBinding
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity
import id.bazrira.submission3.features.favorite.viewmodel.FavoriteViewModel
import id.bazrira.submission3.features.home.ui.adapter.ItemDataAdapter
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment(), ItemCallback {

  private var _binding: FragmentFavoriteTvShowBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: FavoriteViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  private lateinit var itemDataAdapter: ItemDataAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel =
      ViewModelProvider(this@FavoriteTvShowFragment, factory).get(FavoriteViewModel::class.java)

    itemDataAdapter = ItemDataAdapter(this@FavoriteTvShowFragment)

    binding.recyclerViewFavoriteTvShows.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = itemDataAdapter
      setHasFixedSize(true)
    }

    setListData()
  }

  private fun enableEmptyStateEmptyFavoriteMovie() {
    binding.favoriteEmptyState.imgEmptyState.setImageResource(R.drawable.ic_empty_state_favorite)
    binding.favoriteEmptyState.imgEmptyState.contentDescription =
      resources.getString(R.string.empty_state_desc_no_favorite_item_movie)
    binding.favoriteEmptyState.titleEmptyState.text =
      resources.getString(R.string.empty_state_title_no_favorite_item)
    binding.favoriteEmptyState.descEmptyState.text =
      resources.getString(R.string.empty_state_desc_no_favorite_item_tvshow)
  }

  private fun setListData() {
    viewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, { listData ->
      if (listData != null) {
        binding.recyclerViewFavoriteTvShows.adapter?.let { adapter ->
          when (adapter) {
            is ItemDataAdapter -> {
              if (listData.isNullOrEmpty()) {
                binding.recyclerViewFavoriteTvShows.visibility = View.GONE
                enableEmptyStateEmptyFavoriteMovie()

                binding.favoriteEmptyState.root.visibility = View.VISIBLE
              } else {
                binding.recyclerViewFavoriteTvShows.visibility = View.VISIBLE
                itemDataAdapter.submitList(listData)
                itemDataAdapter.notifyDataSetChanged()

                binding.favoriteEmptyState.root.visibility = View.GONE
              }
            }
          }
        }
      }
    })
  }

  override fun onItemClicked(itemData: ItemData) {
    startActivity(
      Intent(
        context,
        DetailActivity::class.java
      )
        .putExtra(DetailActivity.EXTRA_ITEM_ID, itemData.id)
        .putExtra(DetailActivity.EXTRA_ITEM_TYPE, TV_SHOW)
    )
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}