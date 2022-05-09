package id.bazrira.submission2.data.repository.catalogue

import androidx.lifecycle.LiveData
import id.bazrira.submission2.data.model.ItemData

interface CatalogueRepository {

  fun getNowPlayingMovies(): LiveData<List<ItemData>>
  fun getMovieDetail(movieId: Int): LiveData<ItemData>
  fun getTvShowOnTheAir(): LiveData<List<ItemData>>
  fun getTvShowDetail(tvShowId: Int): LiveData<ItemData>
}