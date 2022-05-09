package id.bazrira.submission1.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bazrira.submission1.data.ItemData
import id.bazrira.submission1.databinding.ItemListDataBinding
import id.bazrira.submission1.ui.activity.DetailActivity
import id.bazrira.submission1.utils.ItemCallback
import id.bazrira.submission1.utils.load

class ItemDataAdapter(private val itemCallback: ItemCallback) : RecyclerView.Adapter<ItemDataAdapter.ListViewHolder>() {

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
        binding.imageViewPoster.load(this.poster)

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
