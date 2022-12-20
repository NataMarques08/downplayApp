package com.natamarques.downplayteste.diff

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.downplayteste.databinding.VideoYoutubeListBinding
import com.natamarques.downplayteste.videoAdapterYT.VideoYtAdapter

object DiffCreate {

    fun create(parent : ViewGroup,
               onClickItem: (() -> Unit)?
    ): VideoYtAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VideoYoutubeListBinding.inflate(inflater,parent,false)
        return VideoYtAdapter.MyViewHolder(binding, onClickItem)
    }
}