package com.natamarques.downplayteste.videoAdapterYT


import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.downplayteste.databinding.VideoYoutubeListBinding
import com.natamarques.downplayteste.diff.DiffCall
import com.natamarques.downplayteste.diff.DiffCreate
import com.natamarques.downplayteste.extractor.QualityButtons
import com.natamarques.downplayteste.model_search._YoutubeList


class VideoYtAdapter :
    ListAdapter<_YoutubeList, VideoYtAdapter.MyViewHolder>(DiffCall.diff_callback) {
    var onClickItem: (() -> Unit)? = null



       class MyViewHolder(
          private val itemBinding: VideoYoutubeListBinding,
          private val onClickItem: (() -> Unit)?,
      ) : RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(youtubeList: _YoutubeList) {

            itemBinding.imgVideo.load(youtubeList.items[layoutPosition].snippet.thumbnails.high.url)
            itemBinding.tvVideoName.text = youtubeList.items[layoutPosition].snippet.title
            itemBinding.tvChannelName.text = youtubeList.items[layoutPosition].snippet.channelTitle
            itemBinding.btnDownload.setOnClickListener {

                val intent = Intent(it.context, QualityButtons::class.java)
                intent.putExtra("idVideo",youtubeList.items[layoutPosition].id.videoId)
                it.context.startActivity(intent)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return DiffCreate.create(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}