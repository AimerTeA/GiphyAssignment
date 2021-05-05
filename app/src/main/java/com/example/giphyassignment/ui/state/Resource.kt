package com.example.giphyassignment.ui.state

data class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val message: Throwable?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        fun <T> error(message: Throwable?): Resource<T> {
            return Resource(ResourceState.ERROR, null, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(ResourceState.LOADING, data, null)
        }

        fun <T> idle(data: T? = null): Resource<T> {
            return Resource(ResourceState.IDLE, data, null)
        }
    }

    fun isSuccess(): Boolean = status == ResourceState.SUCCESS

    fun isLoading(): Boolean = status == ResourceState.LOADING

    fun isError(): Boolean = status == ResourceState.ERROR

    fun isIdle(): Boolean = status == ResourceState.IDLE

    inline fun <R> map(block: (T) -> R): Resource<R> {
        return when (status) {
            ResourceState.SUCCESS -> success(data?.let(block))
            ResourceState.LOADING -> loading()
            ResourceState.ERROR -> error(this.message)
            ResourceState.IDLE -> idle()
        }
    }
}