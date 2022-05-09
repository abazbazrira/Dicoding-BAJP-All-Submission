package id.bazrira.submission2.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.bazrira.submission2.data.model.ItemData
import id.bazrira.submission2.data.repository.catalogue.CatalogueRepository
import id.bazrira.submission2.data.repository.catalogue.remote.datasource.CatalogueRemoteDataSource
import id.bazrira.submission2.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission2.data.repository.catalogue.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FakeCatalogRepository(private val remoteDataSource: CatalogueRemoteDataSource) :
  CatalogueRepository {

  override fun getNowPlayingMovies(): LiveData<List<ItemData>> {
    val listMovieResult = MutableLiveData<List<ItemData>>()
    CoroutineScope(IO).launch {
      remoteDataSource.getNowPlayingMovies(object :
        CatalogueRemoteDataSource.LoadNowPlayingMoviesCallback {
        override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
          val movieList = ArrayList<ItemData>()
          for (response in movieResponse) {
            val movie = ItemData(
              response.id,
              response.title,
              response.desc,
              response.poster,
              response.backDrop
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
    CoroutineScope(IO).launch {
      remoteDataSource.getMovieDetail(
        movieId,
        object : CatalogueRemoteDataSource.LoadMovieDetailCallback {
          override fun onMovieDetailReceived(movieResponse: MovieResponse) {
            val movie = ItemData(
              movieResponse.id,
              movieResponse.title,
              movieResponse.desc,
              movieResponse.poster,
              movieResponse.backDrop
            )

            movieResult.postValue(movie)
          }
        })
    }

    return movieResult
  }

  override fun getTvShowOnTheAir(): LiveData<List<ItemData>> {
    val listTvShowResult = MutableLiveData<List<ItemData>>()
    CoroutineScope(IO).launch {
      remoteDataSource.getTvShowOnTheAir(object :
        CatalogueRemoteDataSource.LoadOnTheAirTvShowCallback {
        override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
          val tvShowList = ArrayList<ItemData>()
          for (response in tvShowResponse) {
            val tvShow = ItemData(
              response.id,
              response.title,
              response.desc,
              response.poster,
              response.backDrop
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
    CoroutineScope(IO).launch {
      remoteDataSource.getTvShowDetail(
        tvShowId,
        object : CatalogueRemoteDataSource.LoadTvShowDetailCallback {
          override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
            val tvShow = ItemData(
              tvShowResponse.id,
              tvShowResponse.title,
              tvShowResponse.desc,
              tvShowResponse.poster,
              tvShowResponse.backDrop
            )

            tvShowResult.postValue(tvShow)
          }
        })
    }

    return tvShowResult
  }
}