package com.shahin.meistersearch.general.views

/**
 * class representing restricted View Click Types
 * @param T is any type parameter restricting outputs
 */
sealed class ViewClickType<out T>{
    data class ToOpen<T>(val block: T): ViewClickType<T>()
    data class ToRemove<T>(val block: T): ViewClickType<T>()
}