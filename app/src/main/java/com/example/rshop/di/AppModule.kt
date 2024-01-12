package com.example.rshop.di

import com.example.rshop.data.source.remote.NetworkService
import com.example.rshop.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): NetworkService {
            return retrofit.create(NetworkService::class.java)
        }
    }
