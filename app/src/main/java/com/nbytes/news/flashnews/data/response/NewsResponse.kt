package com.nbytes.news.flashnews.data.db.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("totalResults")
                           val totalResults: Int = 0,
                        @SerializedName("articles")
                           val articles: List<ArticlesItem>?,
                        @SerializedName("status")
                           val status: String = "")