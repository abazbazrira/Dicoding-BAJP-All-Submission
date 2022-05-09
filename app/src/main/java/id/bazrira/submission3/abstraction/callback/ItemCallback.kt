package id.bazrira.submission3.abstraction.callback

import id.bazrira.submission3.data.model.ItemData

interface ItemCallback {
  fun onItemClicked(itemData: ItemData)
}