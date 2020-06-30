package com.nbytes.news.flashnews.data.db.network

object NewsRepository {

    var client: NewsAPIService = NewsAPIService.invoke()

    suspend fun getAllNews(key: String) = client.getAllNews(key)
}