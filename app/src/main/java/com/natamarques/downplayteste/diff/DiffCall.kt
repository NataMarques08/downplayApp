package com.natamarques.downplayteste.diff

import androidx.recyclerview.widget.DiffUtil
import com.natamarques.downplayteste.model_search._YoutubeList


object DiffCall {
    val diff_callback = object : DiffUtil.ItemCallback<_YoutubeList>(){
        override fun areItemsTheSame(oldItem: _YoutubeList,
                                     newItem: _YoutubeList): Boolean {
            return oldItem.items.size == newItem.items.size
        }

        override fun areContentsTheSame(oldItem: _YoutubeList,
                                        newItem: _YoutubeList): Boolean {
            return oldItem == newItem
        }

    }
}