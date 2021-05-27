package com.shahin.meistersearch.general.extensions

import android.content.Context
import com.shahin.meistersearch.data.remote.models.response.error.ErrorResponse

/**
 * extension to extract clean message from error response model
 * it returns a user friendly message
 *
 * @return nullable string
 */
fun ErrorResponse?.extractCleanMessage(context: Context): String? {
    val stringBuilder = StringBuilder()
    this?.errors?.let {
        it.take(it.size).forEach {
            stringBuilder.append(it.status)
            stringBuilder.append("\n")
            stringBuilder.append(it.message)
        }
    }
    return if (stringBuilder.toString().isEmpty()) null else  stringBuilder.toString()
}