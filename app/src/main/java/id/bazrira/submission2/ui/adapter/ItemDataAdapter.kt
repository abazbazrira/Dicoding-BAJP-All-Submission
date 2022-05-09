package id.bazrira.submission2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.databinding.ItemListDataBinding
import id.bazrira.submission2.utils.Constants.API_IMAGE_ENDPOINT
import id.bazrira.submission2.utils.Constants.ENDPOINT_POSTER_SIZE_W185
import id.bazrira.submission2.utils.ItemCallback
import id.bazrira.submission2.utils.load

class ItemDataAdapter(private val itemCallback: ItemCallback) :
  RecyclerView.Adapter<ItemDataAdapter.ListViewHolder>() {

  private val data = ArrayList<ItemData>()

  fun setData(items: ArrayList<ItemData>) {
    data.clear()
    data.addAll(items)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
    val binding =
      ItemListDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ListViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    with(holder) {
      with(data[position]) {
        binding.textViewTitle.text = this.title
        binding.textViewDesc.text = this.desc
        binding.imageViewPoster.load(API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + this.poster)

        binding.cardViewTvShow.setOnClickListener {
          itemCallback.onItemClicked(this)
        }
      }
    }
  }

  inner class ListViewHolder(val binding: ItemListDataBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun getItemCount(): Int = data.size
}
