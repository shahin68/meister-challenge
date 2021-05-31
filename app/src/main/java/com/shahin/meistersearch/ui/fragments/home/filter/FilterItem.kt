package com.shahin.meistersearch.ui.fragments.home.filter

/**
 * Filter Item
 * Representing a Filter with selected status
 */
data class FilterItem(
    val filter: Filter,
    val selected: Boolean = false
)
