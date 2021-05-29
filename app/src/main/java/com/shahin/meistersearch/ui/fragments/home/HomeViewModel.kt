package com.shahin.meistersearch.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import com.shahin.meistersearch.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getToken(): String {
        return repository.getToken()
    }

    private val _searchResult: MutableLiveData<NetworkResult<SearchResponse>> = MutableLiveData()
    val searchResult: LiveData<NetworkResult<SearchResponse>> = _searchResult

    fun search(query: String) {
        viewModelScope.launch {
            _searchResult.postValue(repository.search(query))
        }
    }

    suspend fun searchPaging(query: String): Flow<PagingData<TaskItem>> {
        return repository.searchPaging(query).cachedIn(viewModelScope)
    }
}