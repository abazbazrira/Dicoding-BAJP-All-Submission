package id.bazrira.submission2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(
  var id: Int = 0,
  var title: String = "",
  var poster: String = "",
  var backDrop: String = "",
  var desc: String = ""
) : Parcelable