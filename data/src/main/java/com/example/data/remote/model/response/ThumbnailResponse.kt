package com.example.data.remote.model.response

data class ThumbnailResponse(
    val path: String,
    val extension: String
) {
    fun getImageUrl() =
        "$path.$extension".replace(
            oldValue = HTTP_SCHEME,
            newValue = HTTPS_SCHEME
        )

    companion object {
        private const val HTTP_SCHEME = "http"
        private const val HTTPS_SCHEME = "https"
    }
}