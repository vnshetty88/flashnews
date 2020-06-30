package com.nbytes.news.flashnews.data.db.response

import com.google.gson.annotations.SerializedName

data class Source(@SerializedName("name")
                  val name: String = "",
                  @SerializedName("id")
                  val id: String = "")