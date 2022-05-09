package id.bazrira.submission1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(
  var id: String = "",
  var title: String = "",
  var poster: String = "",
  var backDrop: String = "",
  var desc: String = ""
) : Parcelable