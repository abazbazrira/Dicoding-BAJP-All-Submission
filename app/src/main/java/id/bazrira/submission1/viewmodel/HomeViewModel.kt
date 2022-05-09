package id.bazrira.submission1.viewmodel

import androidx.lifecycle.ViewModel
import id.bazrira.submission1.data.ItemData
import id.bazrira.submission1.utils.DataDummy

class HomeViewModel : ViewModel() {

  fun getListMovie(): ArrayList<ItemData> = DataDummy.generateDataMovieDummy()

  fun getListTvShow(): ArrayList<ItemData> = DataDummy.generateDataTvShowDummy()

}