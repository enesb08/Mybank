package com.enesbaskaya.mybank.di

import android.content.Context
import com.enesbaskaya.mybank.common.Constants
import com.enesbaskaya.mybank.data.api.ApiHelper
import com.enesbaskaya.mybank.data.api.ApiService
import com.enesbaskaya.mybank.data.repository.ApiRepository
import com.enesbaskaya.mybank.domain.repository.ApiRepositoryImp
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return Constants.baseUrl
    }





    @Singleton
    @Provides
    fun provideOkHttp(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }




    @Singleton
    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        var builder= GsonBuilder()
        builder.addSerializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                val annotation = f.getAnnotation(Expose::class.java)
                return if (annotation != null) !annotation.serialize else false
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                val annotation = clazz.getAnnotation(Expose::class.java)
                return if (annotation != null) !annotation.serialize else false
            }
        })
        builder.addDeserializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                val annotation = f.getAnnotation(Expose::class.java)
                return if (annotation != null) !annotation.deserialize else false
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                val annotation = clazz.getAnnotation(Expose::class.java)
                return if (annotation != null) !annotation.deserialize else false
            }
        })

        return builder
    }


    @Singleton
    @Provides
    fun provideConverterFactory(builder: GsonBuilder): Converter.Factory {

        return GsonConverterFactory.create(builder.create())
    }


    @Singleton
    @Provides
    fun provideRxConverterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        okHttpClient: OkHttpClient,
        factory: RxJava2CallAdapterFactory,
        gsonFactory: Converter.Factory,
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(factory)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }




    @Singleton
    @Provides
    fun provideApiHelper(@ApplicationContext context: Context, apiService: ApiService): ApiHelper {
        return ApiHelper(context, apiService)
    }

    @Singleton
    @Provides
    fun provideApiRepository(
        apiHelper: ApiHelper,
    ): ApiRepository {
        return ApiRepositoryImp(apiHelper)
    }

}