package id.bazrira.submission3.data.repository.catalogue.remote.datasource

import androidx.lifecycle.LiveData
import id.bazrira.submission3.abstraction.network.ApiResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.TvShowResponse

interface CatalogueRemoteDataSource {

  fun getNowPlayingMovies(): LiveData<ApiResponse<List<MovieResponse>>>
  fun getOnTheAirTvShows(): LiveData<ApiResponse<List<TvShowResponse>>>
}