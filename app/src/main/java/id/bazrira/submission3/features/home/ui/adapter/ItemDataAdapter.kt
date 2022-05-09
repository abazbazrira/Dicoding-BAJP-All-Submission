package id.bazrira.submission3.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.bazrira.submission3.abstraction.callback.ItemCallback
import id.bazrira.submission3.abstraction.utils.Constants.API_IMAGE_ENDPOINT
import id.bazrira.submission3.abstraction.utils.Constants.ENDPOINT_POSTER_SIZE_W185
import id.bazrira.submission3.abstraction.utils.load
import id.bazrira.submission3.data.model.ItemData
import id.bazrira.submission3.databinding.ItemListDataBinding

class ItemDataAdapter(private val itemCallback: ItemCallback) :
  PagedListAdapter<ItemData, ItemDataAdapter.ListViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemData>() {
      override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
    val binding =
      ItemListDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ListViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    with(holder) {

      val item = getItem(position)

      if (item != null) {

        binding.textViewTitle.text = item.title
        binding.textViewDesc.text = item.desc
        binding.imageViewPoster.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + item.poster)

        binding.cardViewTvShow.setOnClickListener {
          itemCallback.onItemClicked(item)
        }
      }
    }
  }

  inner class ListViewHolder(val binding: ItemListDataBinding) :
    RecyclerView.ViewHolder(binding.root)
}
