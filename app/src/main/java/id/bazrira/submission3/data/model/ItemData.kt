package id.bazrira.submission3.data.model

data class ItemData(
  var id: Int? = 0,
  var title: String = "",
  var poster: String = "",
  var backDrop: String = "",
  var desc: String = "",
  var isFavorite: Boolean = false,
)