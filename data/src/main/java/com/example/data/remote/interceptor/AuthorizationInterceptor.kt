package com.example.data.remote.interceptor

import com.example.data.util.extension.timeInSeconds
import com.example.nativelib.NativeLib.hashAlgorithm
import com.example.nativelib.NativeLib.privateKey
import com.example.nativelib.NativeLib.publicKey
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Calendar

class AuthorizationInterceptor : Interceptor {

    private val timeStamped = "${Calendar.getInstance().timeInSeconds()}"

    private val hash = (timeStamped + privateKey() + publicKey()).generateHash()

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = buildNewUrl(chain)

        return chain.proceed(
            buildNewRequest(
                chain = chain,
                newUrl = newUrl
            )
        )
    }

    private fun buildNewUrl(chain: Interceptor.Chain): HttpUrl =
        chain.request().url
            .newBuilder()
            .addQueryParameter(
                name = MARVEL_API_TS_QUERY,
                value = timeStamped
            )
            .addQueryParameter(
                name = MARVEL_API_KEY_QUERY,
                value = publicKey()
            )
            .addQueryParameter(
                name = MARVEL_API_HASH_QUERY,
                value = hash
            )
            .build()

    private fun buildNewRequest(
        chain: Interceptor.Chain,
        newUrl: HttpUrl
    ): Request =
        chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

    private fun String.generateHash() =
        MessageDigest.getInstance(hashAlgorithm()).let { md ->
            BigInteger(
                BIG_INTEGER_SIGNUM,
                md.digest(toByteArray())
            ).toString(BIG_INTEGER_RADIX)
                .padStart(
                    length = HASH_LENGTH,
                    padChar = HASH_CHAR
                )
        }

    companion object {
        // REQUEST QUERIES
        private const val MARVEL_API_TS_QUERY = "ts"
        private const val MARVEL_API_KEY_QUERY = "apikey"
        private const val MARVEL_API_HASH_QUERY = "hash"

        // BIG INTEGER
        private const val BIG_INTEGER_SIGNUM = 1
        private const val BIG_INTEGER_RADIX = 16

        // REQUEST HASH
        private const val HASH_LENGTH = 32
        private const val HASH_CHAR = '0'
    }
}