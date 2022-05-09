package id.bazrira.submission2.data.repository.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSource
import id.bazrira.submission2.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission2.data.repository.catalogue.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogueRepositoryImpl private constructor(private val remoteDataSource: CatalogueRemoteDataSource) :
  CatalogueRepository {

  companion object {
    @Volatile
    private var instance: CatalogueRepositoryImpl? = null

    fun getInstance(remoteDataSource: CatalogueRemoteDataSource): CatalogueRepositoryImpl =
      instance ?: synchronized(this) {
        instance ?: CatalogueRepositoryImpl(remoteDataSource)
      }
  }

  override fun getNowPlayingMovies(): LiveData<List<ItemData>> {
    val listMovieResult = MutableLiveData<List<ItemData>>()
    CoroutineScope(Dispatchers.IO).launch {
      remoteDataSource.getNowPlayingMovies(object :
        CatalogueRemoteDataSource.LoadNowPlayingMoviesCallback {
        override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
          val movieList = ArrayList<ItemData>()
          for (response in movieResponse) {
            val movie = ItemData(
              id = response.id,
              title = response.title,
              desc = response.desc,
              poster = response.poster,
              backDrop = response.backDrop
            )
            movieList.add(movie)
          }
          listMovieResult.postValue(movieList)
        }
      })
    }

    return listMovieResult
  }

  override fun getMovieDetail(movieId: Int): LiveData<ItemData> {
    val movieResult = MutableLiveData<ItemData>()
    CoroutineScope(Dispatchers.IO).launch {
      remoteDataSource.getMovieDetail(
        movieId,
        object : CatalogueRemoteDataSource.LoadMovieDetailCallback {
          override fun onMovieDetailReceived(movieResponse: MovieResponse) {
            val movie = ItemData(
              id = movieResponse.id,
              title = movieResponse.title,
              desc = movieResponse.desc,
              poster = movieResponse.poster,
              backDrop = movieResponse.backDrop
            )

            movieResult.postValue(movie)
          }
        })
    }

    return movieResult
  }

  override fun getTvShowOnTheAir(): LiveData<List<ItemData>> {
    val listTvShowResult = MutableLiveData<List<ItemData>>()
    CoroutineScope(Dispatchers.IO).launch {
      remoteDataSource.getTvShowOnTheAir(object :
        CatalogueRemoteDataSource.LoadOnTheAirTvShowCallback {
        override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
          val tvShowList = ArrayList<ItemData>()
          for (response in tvShowResponse) {
            val tvShow = ItemData(
              id = response.id,
              title = response.title,
              desc = response.desc,
              poster = response.poster,
              backDrop = response.backDrop
            )
            tvShowList.add(tvShow)
          }

          listTvShowResult.postValue(tvShowList)
        }
      })
    }

    return listTvShowResult
  }

  override fun getTvShowDetail(tvShowId: Int): LiveData<ItemData> {
    val tvShowResult = MutableLiveData<ItemData>()
    CoroutineScope(Dispatchers.IO).launch {
      remoteDataSource.getTvShowDetail(
        tvShowId,
        object : CatalogueRemoteDataSource.LoadTvShowDetailCallback {
          override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
            val tvShow = ItemData(
              id = tvShowResponse.id,
              tvShowResponse.title,
              tvShowResponse.desc,
              tvShowResponse.poster,
              tvShowResponse.backDrop
            )

            tvShowResult.postValue(tvShow)
          }
        })
    }
/*
    val movieResult = MutableLiveData<ItemData>()
    CoroutineScope(Dispatchers.IO).launch {
      remoteDataSource.getMovieDetail(
        tvShowId,
        object : CatalogueRemoteDataSource.LoadMovieDetailCallback {
          override fun onMovieDetailReceived(movieResponse: MovieResponse) {
            val tvShow = ItemData(
              id = movieResponse.id,
              title = movieResponse.title,
              desc = movieResponse.desc,
              poster = movieResponse.poster,
              backDrop = movieResponse.backDrop
            )

            movieResult.postValue(tvShow)
          }
        })
    }*/

    return tvShowResult
  }

}