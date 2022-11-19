package com.submission.storyapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.submission.storyapp.data.remote.service.ApiService
import com.submission.storyapp.utillities.base.app.NetworkConfig
import com.submission.storyapp.utillities.network.*
import com.submission.storyapp.utillities.network.interceptor.HttpRequestInterceptor
import com.submission.storyapp.utillities.network.interceptor.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "base_url"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = networkConfig.isDev())
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(): HttpRequestInterceptor {
        return createHttpRequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideNoInternetInterceptor(@ApplicationContext context: Context): NoConnectionInterceptor {
        return createNoConnection(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        noConnectionInterceptor: NoConnectionInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor
    ): OkHttpClient {
        return createOkHttpClient(
            interceptors = arrayOf(
                noConnectionInterceptor,
                httpLoggingInterceptor,
                chuckerInterceptor,
                httpRequestInterceptor
            ),
            context = context
        )
    }
    @Provides
    @Singleton
    fun provideAuthService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = createRetrofitWithMoshi<ApiService>(
        okHttpClient = okHttpClient,
        moshi = moshi,
        baseUrl = baseUrl
    )

}