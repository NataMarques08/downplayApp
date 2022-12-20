package com.natamarques.downplayteste.repository

import com.natamarques.downplayteste.helper.Constants
import com.natamarques.downplayteste.rest.YoutubeService
import javax.inject.Inject


class RepositoryYT
@Inject
constructor(private val youtubeService: YoutubeService){
    suspend fun getVideos(q : String) = youtubeService.getVideos(
        "snippet",q,
        "100",
        "br",Constants.API_KEY)
}