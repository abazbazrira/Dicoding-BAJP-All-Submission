package id.bazrira.submission3.features.home.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import id.bazrira.submission3.abstraction.callback.ItemCallback
import id.bazrira.submission3.abstraction.network.Status
import id.bazrira.submission3.abstraction.utils.Constants.MOVIE
import id.bazrira.submission3.abstraction.utils.ViewModelFactory
import id.bazrira.submission3.abstraction.utils.showToast
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.databinding.FragmentMovieBinding
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity.Companion.EXTRA_ITEM_ID
import id.bazrira.submission3.features.detail.ui.activity.DetailActivity.Companion.EXTRA_ITEM_TYPE
import id.bazrira.submission3.features.home.ui.adapter.ItemDataAdapter
import id.bazrira.submission3.features.home.viewmodel.HomeViewModel
import javax.inject.Inject

class MovieFragment : DaggerFragment(), ItemCallback {

  private lateinit var viewModel: HomeViewModel

  @Inject
  lateinit var factory: ViewModelFactory

  private var _binding: FragmentMovieBinding? = null
  private val binding get() = _binding!!

  private lateinit var itemDataAdapter: ItemDataAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentMovieBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    activity?.let {
      it.let {
        viewModel = ViewModelProvider(
          it,
          factory
        )[HomeViewModel::class.java]
      }
    }

    itemDataAdapter = ItemDataAdapter(this@MovieFragment)

    binding.recyclerViewItems.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = itemDataAdapter
      setHasFixedSize(true)
    }

    setListData()

    binding.swipeRefresh.setOnRefreshListener {
      setListData()
      binding.swipeRefresh.isRefreshing = false
    }
  }

  private fun setListData() {
    viewModel.getListMovie().observe(viewLifecycleOwner, { listData ->
      if (listData != null) {
        when (listData.status) {
          Status.LOADING -> binding.swipeRefresh.isRefreshing = true
          Status.SUCCESS -> {
            binding.swipeRefresh.isRefreshing = false
            itemDataAdapter.submitList(listData.data)
            itemDataAdapter.notifyDataSetChanged()
          }
          Status.ERROR -> {
            binding.swipeRefresh.isRefreshing = false
            requireContext().showToast("Check your internet connection")
          }
        }
      }
    })
  }

  override fun onItemClicked(itemData: ItemData) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra(EXTRA_ITEM_ID, itemData.id)
    intent.putExtra(EXTRA_ITEM_TYPE, MOVIE)

    startActivity(intent)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }
}