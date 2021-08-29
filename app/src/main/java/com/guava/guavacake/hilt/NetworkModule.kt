package com.guava.guavacake.hilt

import com.google.gson.GsonBuilder
import com.guava.guavacake.Constants
import com.guava.guavacake.network.GuavaApiService
import com.guava.guavacake.network.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MockInterceptor())
            .build()
    }

    @Provides
    fun provideAPIService(okHttpClient: OkHttpClient): GuavaApiService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(GuavaApiService::class.java)
    }
}