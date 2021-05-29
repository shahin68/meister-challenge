package com.shahin.meistersearch.data.remote.sources.paging

import com.shahin.meistersearch.data.remote.models.body.FilterBody
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import com.shahin.meistersearch.data.remote.models.response.search.SearchResponse
import com.shahin.meistersearch.data.remote.models.response.search.SearchResult
import com.shahin.meistersearch.data.remote.models.response.search.items.TaskItem
import retrofit2.Response

class FakePagingDataSource: PagingDataSource {

    private var currentPage = 0

    private val taskItem = TaskItem(
        id = 0
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
                val list = arrayListOf<TaskItem>()
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