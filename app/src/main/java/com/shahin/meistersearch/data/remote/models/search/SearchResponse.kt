package com.shahin.meistersearch.data.remote.models.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.meistersearch.data.remote.models.paging.PagingResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(

	@SerializedName("paging")
	val pagingResult: PagingResult? = null,

	@SerializedName("results")
	val searchResult: SearchResult? = null
) : Parcelable


