package id.bazrira.submission2.network

import id.bazrira.submission2.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Network {

  val instance: Services by lazy {
    builder().create(Services::class.java)
  }

  private fun okHttpClient(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
      .retryOnConnectionFailure(true)
      .addInterceptor(NetworkInterceptor())
      .pingInterval(30, TimeUnit.SECONDS)
      .readTimeout(1, TimeUnit.MINUTES)
      .connectTimeout(1, TimeUnit.MINUTES)

    /**
     * Set logging connection
     * */

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//    logging.setLevel(HttpLoggingInterceptor.Level.NONE)
    okHttpClient.addInterceptor(logging)

    return okHttpClient.build()
  }

  private fun builder(baseUrl: String = BuildConfig.BASE_URL): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttpClient())
      .build()
  }
}