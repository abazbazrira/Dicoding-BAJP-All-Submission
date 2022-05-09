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
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.databinding.FragmentFavoriteMovieBinding
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity.Companion.EXTRA_ITEM_ID
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity.Companion.EXTRA_ITEM_TYPE
import id.bazrira.submission3.features.favorite.viewmodel.FavoriteViewModel
import id.bazrira.submission3.features.home.ui.adapter.ItemDataAdapter
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), ItemCallback {

  private var _binding: FragmentFavoriteMovieBinding? = null
  private val binding get() = _binding!!

  private lateinit var viewModel: FavoriteViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  private lateinit var itemDataAdapter: ItemDataAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel =
      ViewModelProvider(this@FavoriteMovieFragment, factory).get(FavoriteViewModel::class.java)

    itemDataAdapter = ItemDataAdapter(this@FavoriteMovieFragment)

    binding.recyclerViewFavoriteMovies.apply {
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
      resources.getString(R.string.empty_state_desc_no_favorite_item_movie)
  }

  private fun setListData() {
    viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, { listData ->
      if (listData != null) {
        binding.recyclerViewFavoriteMovies.adapter?.let { adapter ->
          when (adapter) {
            is ItemDataAdapter -> {
              if (listData.isNullOrEmpty()) {
                binding.recyclerViewFavoriteMovies.visibility = View.GONE
                enableEmptyStateEmptyFavoriteMovie()

                binding.favoriteEmptyState.root.visibility = View.VISIBLE
              } else {
                binding.recyclerViewFavoriteMovies.visibility = View.VISIBLE
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
        .putExtra(EXTRA_ITEM_ID, itemData.id)
        .putExtra(EXTRA_ITEM_TYPE, MOVIE)
    )
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}