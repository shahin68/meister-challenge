package com.shahin.meistersearch.ui.fragments.home.filter

/**
 * Filter Types (For Demonstration Purposes Only)
 *
 * Note: Usually, these values will be received from server and there will be no need
 * to create such classes
 * The reason is that at these kinds of values are subjected to change at any point and
 * should not be considered definitive like below
 */
enum class Filter(val status: Int?) {
    ALL(null),
    ACTIVE(1),
    ARCHIVED(18)
}
