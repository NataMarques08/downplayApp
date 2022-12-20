package com.natamarques.downplayteste.rest

import com.natamarques.downplayteste.helper.Constants
import com.natamarques.downplayteste.model_search._YoutubeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET(Constants.END_POINT)
    suspend fun getVideos(
        @Query("part") part : String,
        @Query("q") q : String,
        @Query("maxResults") maxResults : String,
        @Query("regionCode") regionCode : String,
        @Query("key") key : String
    ) : Response<_YoutubeList>
}