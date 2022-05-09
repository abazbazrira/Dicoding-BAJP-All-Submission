package id.bazrira.submission1.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.bazrira.submission1.data.ItemData
import id.bazrira.submission1.databinding.FragmentMovieBinding
import id.bazrira.submission1.ui.activity.DetailActivity
import id.bazrira.submission1.ui.activity.DetailActivity.Companion.EXTRA_ITEM_ID
import id.bazrira.submission1.ui.activity.DetailActivity.Companion.EXTRA_ITEM_TYPE
import id.bazrira.submission1.ui.adapter.ItemDataAdapter
import id.bazrira.submission1.utils.Constants.MOVIE
import id.bazrira.submission1.utils.ItemCallback
import id.bazrira.submission1.viewmodel.HomeViewModel


class MovieFragment : Fragment(), ItemCallback {

  private val viewModel: HomeViewModel by activityViewModels()

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

    itemDataAdapter = ItemDataAdapter(this@MovieFragment)

    binding.recyclerViewMovies.apply {
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
    val listData = viewModel.getListMovie()
    itemDataAdapter.setData(listData)
  }

  override fun onDestroyView() {
    super.onDestroyView()

    _binding = null
  }

  override fun onItemClicked(itemData: ItemData) {
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra(EXTRA_ITEM_ID, itemData.id)
    intent.putExtra(EXTRA_ITEM_TYPE, MOVIE)

    startActivity(intent)
  }
}