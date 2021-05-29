package com.shahin.meistersearch.data.remote.models.response.paging

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagingResult(

    @SerializedName("results_per_page")
    val resultsPerPage: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("total_results")
    val totalResults: Int
) : Parcelable