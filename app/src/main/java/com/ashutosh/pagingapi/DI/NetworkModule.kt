package com.ashutosh.pagingapi.DI

import android.content.Context
import androidx.room.Room
import com.ashutosh.pagingapi.data.API.PagingApi
import com.ashutosh.pagingapi.Utils.Constants.BASE_URL
import com.ashutosh.pagingapi.data.dao.PagingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PagingDatabase {
        return Room.databaseBuilder(
            context,
            PagingDatabase::class.java,
            "PAGING_DB"
        ).build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("retrofit")
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }


    @Singleton
    @Provides
    fun providesPagingAPI(@Named("retrofit") retrofit: Retrofit): PagingApi {
        return retrofit.create(PagingApi::class.java)
    }


}