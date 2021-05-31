package com.shahin.meistersearch.data.remote.sources.paging

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskResult
import retrofit2.Response

class FakePagingDataAndroidSource: PagingDataSource {
    private var currentPage = 0

    private val taskItem = TaskResult(
        id = 0,
        name = "",
        status = 1,
        sectionId = 1
    )

    override suspend fun search(
        filterBody: FilterBody,
        page: Int,
        pageLimit: Int
    ): Response<SearchResponse> {
        return when (filterBody) {
            FilterBody() -> {
                Response.success(
                    SearchResponse(
                        PagingResult(0,0,0,0),
                        SearchResult(
                            emptyList(),
                            emptyList(),
                            emptyList()
                        )
                    )
                )
            }
            FilterBody(
                text = "hello"
            ) -> {
                currentPage =+ 1
                val list = arrayListOf<TaskResult>()
                for (i in 0 until 50) {
                    list.add(taskItem)
                }
                Response.success(
                    SearchResponse(
                        PagingResult(
                            50,
                            3,
                            currentPage,
                            50
                        ),
                        SearchResult(
                            emptyList(),
                            list,
                            emptyList()
                        )
                    )
                )
            }
            else -> {
                Response.success(
                    SearchResponse(
                        PagingResult(0,0,0,0),
                        SearchResult(
                            emptyList(),
                            emptyList(),
                            emptyList()
                        )
                    )
                )
            }
        }
    }
}