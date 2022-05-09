package id.bazrira.submission2.data.repository.catalogue.remote.datasource

import id.bazrira.submission2.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission2.data.repository.catalogue.remote.response.TvShowResponse
import id.bazrira.submission2.network.Network
import id.bazrira.submission2.utils.EspressoIdlingResource
import retrofit2.await

class CatalogueRemoteDataSource {

  companion object {
    @Volatile
    private var instance: CatalogueRemoteDataSource? = null

    fun getInstance(): CatalogueRemoteDataSource =
      instance ?: synchronized(this) {
        instance ?: CatalogueRemoteDataSource()
      }
  }

  suspend fun getNowPlayingMovies(
    callback: LoadNowPlayingMoviesCallback
  ) {
    EspressoIdlingResource.increment()
    Network.instance.getNowPlayingMovies().await().result?.let { listMovie ->
      callback.onAllMoviesReceived(
        listMovie
      )
      EspressoIdlingResource.decrement()
    }
  }

  suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
    EspressoIdlingResource.increment()
    Network.instance.getDetailMovie(movieId).await().let { movie ->
      callback.onMovieDetailReceived(
        movie
      )
      EspressoIdlingResource.decrement()
    }
  }

  suspend fun getTvShowOnTheAir(callback: LoadOnTheAirTvShowCallback) {
    EspressoIdlingResource.increment()
    Network.instance.getOnTheAirTvShow().await().result?.let { listTvShow ->
      callback.onAllTvShowsReceived(
        listTvShow
      )
      EspressoIdlingResource.decrement()
    }
  }

  suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
    EspressoIdlingResource.increment()
    Network.instance.getDetailTvShow(tvShowId).await().let { tvShow ->
      callback.onTvShowDetailReceived(
        tvShow
      )
      EspressoIdlingResource.decrement()
    }
  }

  interface LoadNowPlayingMoviesCallback {
    fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
  }

  interface LoadMovieDetailCallback {
    fun onMovieDetailReceived(movieResponse: MovieResponse)
  }

  interface LoadOnTheAirTvShowCallback {
    fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
  }

  interface LoadTvShowDetailCallback {
    fun onTvShowDetailReceived(tvShowResponse: TvShowResponse)
  }

}