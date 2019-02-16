package com.example.imgurgallery.api

// A generic class that contains data and status about loading this data.
data class Resource<T>(
    val result: Result,
    val data: T? = null,
    val success: Boolean? = null,
    val throwable: Throwable? = null,
    val error: Error? = null
) {

    enum class Result {
        SUCCESS, ERROR, LOADING, FINISHED
    }

    companion object {
        fun <T> success(resource: Resource<T>): Resource<T> {
            return resource.copy(result = Result.SUCCESS)
        }

        fun <T> error(throwable: Throwable?): Resource<T> {
            return Resource(result = Result.ERROR, throwable = throwable)
        }

        fun <T> loading(): Resource<T> {
            return Resource(result = Result.LOADING)
        }

        fun <T> finished(): Resource<T> {
            return Resource(result = Result.FINISHED)
        }
    }
}

data class GalleryItem(
    val images_count: Int?,
    val images: List<Image>,
    val title: String?,
    val description: String?,
    val link: String?
)

data class Image(val title: String?, val description: String?, val link: String?)

data class Error(val error: String)