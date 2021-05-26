package com.shahin.meistersearch.data.remote.models.response.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.response.paging.PagingResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(

	@SerializedName("paging")
	val pagingResult: PagingResult? = null,

	@SerializedName("results")
	val searchResult: SearchResult? = null
) : Parcelable


