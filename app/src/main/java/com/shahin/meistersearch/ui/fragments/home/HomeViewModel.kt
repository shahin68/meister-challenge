package com.shahin.meistersearch.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.shahin.meistersearch.data.Repository
import com.shahin.meistersearch.data.local.models.relations.project_section.ProjectWithSections
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import com.shahin.meistersearch.network.NetworkResult
import com.shahin.meistersearch.ui.fragments.home.models.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun searchPaging(query: String): Flow<PagingData<TaskResult>> {
        return repository.searchPaging(query).cachedIn(viewModelScope)
    }

    suspend fun searchPagingWithDb(query: String): Flow<PagingData<TaskItem>> {
        return repository.searchPagingWithDb(query).map { pagingData ->
                pagingData.filter { it.task.taskName.contains(query, ignoreCase = true) }
                    .map {
                    val projects = getProjectsWithSections(it.sections.firstOrNull()?.sectionProjectId ?: 0)
                    TaskItem(
                        it.task.taskName,
                        projects.firstOrNull()?.project?.projectName ?: ""
                    )
                }
            }
    }

    private suspend fun getProjectsWithSections(projectId: Int): List<ProjectWithSections> {
        return repository.getProjectsWithSections(projectId)
    }
}