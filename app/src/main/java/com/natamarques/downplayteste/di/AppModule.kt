package com.natamarques.downplayteste.di

import com.natamarques.downplayteste.helper.Constants
import com.natamarques.downplayteste.rest.YoutubeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getUrlInstance() = Constants.BASE_URL


    @Provides
    fun getRetrofitInstance(BASE_URL:String) : YoutubeService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeService::class.java)

}