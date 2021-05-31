package com.shahin.meistersearch.general.views

/**
 * class representing restricted View Click Types
 * @param T is any type parameter restricting outputs
 */
sealed class ViewClickCallback<out T>{
    data class ToOpen<T>(val data: T): ViewClickCallback<T>()
    data class ToRemove<T>(val Data: T): ViewClickCallback<T>()
}