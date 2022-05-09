package id.bazrira.submission3.network

import android.util.Log
import id.bazrira.submission3.abstraction.utils.Constants.RESPONSE_CODE_INTERNAL_SERVER_ERROR
import id.bazrira.submission3.abstraction.utils.Constants.RESPONSE_CODE_NOT_FOUND
import id.bazrira.submission3.abstraction.utils.Constants.RESPONSE_CODE_UNAUTHORIZED
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()

    /**
     * Set Basic Auth on Header
     **/
//        val authToken = Credentials.basic("{username}", "{password}")
//        val headers: Headers = request.headers.newBuilder().add("Authorization", authToken).build()
//        request = request.newBuilder().headers(headers).build()

    val response = chain.proceed(request)

    when (response.code) {
      RESPONSE_CODE_INTERNAL_SERVER_ERROR -> Log.e("ERROR_RESPONSE", response.message)
      RESPONSE_CODE_UNAUTHORIZED -> Log.e("ERROR_RESPONSE", response.message)
      RESPONSE_CODE_NOT_FOUND -> Log.e("ERROR_RESPONSE : ", response.message)
    }

    return response
  }
}