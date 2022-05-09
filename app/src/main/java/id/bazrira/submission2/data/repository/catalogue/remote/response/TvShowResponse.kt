package id.bazrira.submission2.data.repository.catalogue.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
  @SerializedName("id")
  var id: Int = 0,
  @SerializedName("name")
  var title: String = "",
  @SerializedName("overview")
  var desc: String = "",
  @SerializedName("poster_path")
  var poster: String = "",
  @SerializedName("backdrop_path")
  var backDrop: String = ""
)