package com.nbytes.news.flashnews.data.db.network

import com.nbytes.news.flashnews.data.db.response.NewsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "a0d3ed25d07742adbc1b585780f79580"
const val BASE_URL = "https://newsapi.org/v2/"
// everything?q=bitcoin&from=2020-05-30&sortBy=publishedAt&apiKey=a0d3ed25d07742adbc1b585780f79580

interface NewsAPIService {

    @GET(value = "everything")
    suspend fun getAllNews(
        @Query(value = "q") about: String,
        @Query(value = "sortBy") publishedAt: String = "publishedAt"
    ): NewsResponse


    @GET(value = "top-headlines")
    suspend fun getTopHeadLines(): NewsResponse


    companion object {
        operator fun invoke(): NewsAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .addQueryParameter("sources", "google-news-in")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPIService::class.java)
        }
    }
}