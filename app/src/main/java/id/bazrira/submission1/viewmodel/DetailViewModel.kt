package id.bazrira.submission1.viewmodel

import androidx.lifecycle.ViewModel
import id.bazrira.submission1.data.ItemData
import id.bazrira.submission1.utils.Constants.MOVIE
import id.bazrira.submission1.utils.DataDummy

class DetailViewModel : ViewModel() {

  fun getDetailData(id: String, type: String): ItemData {
    return if (type == MOVIE) DataDummy.generateDataMovieDummy().first {
      it.id == id
    } else {
      DataDummy.generateDataTvShowDummy().first {
        it.id == id
      }
    }
  }
}