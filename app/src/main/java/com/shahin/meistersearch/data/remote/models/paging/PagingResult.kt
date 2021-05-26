package com.shahin.meistersearch.data.remote.models.paging

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagingResult(

    @SerializedName("results_per_page")
    val resultsPerPage: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("current_page")
    val currentPage: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable