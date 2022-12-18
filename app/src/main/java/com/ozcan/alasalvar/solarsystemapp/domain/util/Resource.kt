package com.ozcan.alasalvar.solarsystemapp.domain.util

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val Resource<*>.isSuccess
    get() = this is Resource.Success && data != null