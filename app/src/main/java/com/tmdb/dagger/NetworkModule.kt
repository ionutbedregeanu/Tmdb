package com.tmdb.dagger

import com.tmdb.network.ConfigurationService
import com.tmdb.network.TrendingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "cd31c85871d61f80967a087c5a7a151c"
private const val API_KEY_QUERY = "api_key"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClientInterceptor().build())
            .build()
    }

    private fun getHttpClientInterceptor() =
        OkHttpClient.Builder().addInterceptor(
            Interceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(API_KEY_QUERY, API_KEY)
                    .build()

                // Request customization: add request headers
                val requestBuilder: Request.Builder = original.newBuilder().url(url)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
        )

    @Provides
    fun provideTrendingService(retrofit: Retrofit): TrendingService = retrofit.create(TrendingService::class.java)

    @Provides
    fun provideConfigurationService(retrofit: Retrofit): ConfigurationService = retrofit.create(ConfigurationService::class.java)
}
