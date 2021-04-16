package com.stefanusj.weatherme.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stefanusj.weatherme.repository.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): StethoInterceptor = StethoInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        stethoInterceptor: StethoInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(stethoInterceptor)
            .addNetworkInterceptor {
                val request = it.request()
                return@addNetworkInterceptor it.proceed(request)
            }.build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(
        converter: Converter.Factory,
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(WebService.BASE_URL)
        .addConverterFactory(converter)
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

}