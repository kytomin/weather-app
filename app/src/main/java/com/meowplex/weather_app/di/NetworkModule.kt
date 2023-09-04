package com.meowplex.weather_app.di

import android.app.Application
import com.meowplex.weather_app.BuildConfig
import com.meowplex.weather_app.Constants.OK_HTTP_CACHE_SIZE
import com.meowplex.weather_app.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpCache(app: Application): Cache {
        return Cache(app.cacheDir, OK_HTTP_CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun providesApiKeyInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()
            val request: Request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache, apiKeyInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun weatherApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create()
    }


}