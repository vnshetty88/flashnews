package com.nbytes.news.flashnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nbytes.news.flashnews.data.db.network.NewsRepository
import com.nbytes.news.flashnews.data.db.response.NewsResponse
import kotlinx.coroutines.Dispatchers

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val news: LiveData<NewsResponse> = liveData(Dispatchers.IO) {
        try {
            emit(NewsRepository.client.getTopHeadLines())
        } catch (e: Exception) {
            e.stackTrace
        }
    }

}