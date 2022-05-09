package id.bazrira.submission1.utils

import id.bazrira.submission1.data.ItemData

interface ItemCallback {
  fun onItemClicked(itemData: ItemData)
}