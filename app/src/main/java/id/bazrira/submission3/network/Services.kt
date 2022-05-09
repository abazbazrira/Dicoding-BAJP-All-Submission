package id.bazrira.submission3.network

import id.bazrira.submission3.BuildConfig.API_KEY
import id.bazrira.submission3.data.repository.catalogue.remote.response.ListResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.MovieResponse
import id.bazrira.submission3.data.repository.catalogue.remote.response.TvShowResponse
import id.bazrira.submission3.abstraction.utils.Constants.BODY_API_KEY
import id.bazrira.submission3.abstraction.utils.Constants.BODY_MOVIE_ID
import id.bazrira.submission3.abstraction.utils.Constants.BODY_TV_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

  companion object {
    private const val NOW_PLAYING_MOVIES = "movie/now_playing"
    private const val DETAIL_MOVIE = "movie/{movie_id}"
    private const val ON_THE_AIR_TV_SHOW = "tv/on_the_air"
    private const val DETAIL_TV_SHOW = "tv/{tv_id}"
  }

  @GET(NOW_PLAYING_MOVIES)
  fun getNowPlayingMovies(
    @Query(BODY_API_KEY) apiKey: String = API_KEY
  ): Call<ListResponse<MovieResponse>>

  @GET(ON_THE_AIR_TV_SHOW)
  fun getOnTheAirTvShow(
    @Query(BODY_API_KEY) apiKey: String = API_KEY
  ): Call<ListResponse<TvShowResponse>>
}