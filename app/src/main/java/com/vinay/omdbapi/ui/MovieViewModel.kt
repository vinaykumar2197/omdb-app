package com.vinay.omdbapi.ui

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.vinay.omdbapi.network.ApiHelper
import com.vinay.omdbapi.paging.MoviePagingSource
import com.vinay.omdbapi.db.DatabaseHelper
import com.vinay.omdbapi.model.detail.DetailResponse
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MovieViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper?)
    : ViewModel()
{
    private val query = MutableLiveData<String>()

    private val detailResponse = MutableLiveData<DetailResponse>()



    init {
        query.value = "Test"
    }

    val listSearchedData = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(apiHelper, query)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }



     fun fetchMovieDetails(imdbID : String) {
        viewModelScope.launch {
            try {
                val detail = apiHelper.getDetail(imdbID)
                detailResponse.postValue(detail)
            } catch (e: Exception) {

            }
        }
    }

    fun getDetail(): LiveData<DetailResponse> {
        return detailResponse
    }



}