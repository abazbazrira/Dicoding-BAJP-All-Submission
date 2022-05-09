package id.bazrira.submission3.data.repository.catalogue.remote.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.bazrira.submission3.abstraction.network.ApiResponse
import id.bazrira.submission3.abstraction.utils.EspressoIdlingResource
import id.bazrira.submission3.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.TvShowResponse
import id.bazrira.submission3.network.Services
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class CatalogueRemoteDataSourceImpl @Inject constructor(
  private val services: Services
) : CatalogueRemoteDataSource {

  override fun getNowPlayingMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
    EspressoIdlingResource.increment()
    val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val response = services.getNowPlayingMovies().await()
        resultMovieResponse.postValue(ApiResponse.success(response.result!!))
      } catch (e: IOException) {
        e.printStackTrace()
        resultMovieResponse.postValue(
          ApiResponse.error(
            e.message.toString(),
            mutableListOf()
          )
        )
      }
    }
    EspressoIdlingResource.decrement()
    return resultMovieResponse
  }

  override fun getOnTheAirTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
    EspressoIdlingResource.increment()
    val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val response = services.getOnTheAirTvShow().await()
        resultTvShowResponse.postValue(ApiResponse.success(response.result!!))
      } catch (e: IOException) {
        e.printStackTrace()
        resultTvShowResponse.postValue(
          ApiResponse.error(
            e.message.toString(),
            mutableListOf()
          )
        )
      }
    }
    EspressoIdlingResource.decrement()
    return resultTvShowResponse
  }
}