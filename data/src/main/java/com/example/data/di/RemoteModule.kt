package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.remote.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        converter: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converter)
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptors: Set<Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                interceptors.forEach { addInterceptor(it) }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptors(): Set<Interceptor> {
        return setOf()
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    companion object {
        private const val NETWORK_TIMEOUT = 10L
    }
}