package com.example.rshop.di

import android.content.Context
import androidx.room.Room
import com.example.rshop.data.source.local.favorite.FavoriteDAO
import com.example.rshop.data.source.local.favorite.FavoriteDatabase
import com.example.rshop.data.source.remote.NetworkService
import com.example.rshop.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): FavoriteDatabase =
        Room.databaseBuilder(context, FavoriteDatabase::class.java, "productDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleDao(productDb: FavoriteDatabase): FavoriteDAO {
        return productDb.getFavoriteFromDao()
    }
    }
