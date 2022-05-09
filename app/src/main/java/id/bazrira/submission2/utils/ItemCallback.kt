package id.bazrira.submission2.utils

import id.bazrira.submission2.data.model.ItemData

interface ItemCallback {
  fun onItemClicked(itemData: ItemData)
}