package com.natamarques.downplayteste.extractor

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.widget.Button
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.downplayteste.databinding.ActivityQualityButtonsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QualityButtons : AppCompatActivity() {
    private lateinit var _binding : ActivityQualityButtonsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityQualityButtonsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val bundle = intent.extras
        val idVideo = bundle?.getString("idVideo")
        val youtubeLink = "https://youtube.com/watch?v="+idVideo.toString()
        getLinkExtractor(youtubeLink)


    }
    fun getLinkExtractor(url : String) = GlobalScope.launch(Dispatchers.Main){


        val extractor = @SuppressLint("StaticFieldLeak")
        object : YouTubeExtractor(this@QualityButtons){
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                try {
                    for (i in 0..ytFiles!!.size()) {
                        val itag = ytFiles.keyAt(i)
                        val ytFile = ytFiles[itag]
                        setButtonsQuality(videoMeta!!.title,ytFile)
                    }
                }catch (e : Exception){
                    Log.d("ERRO", "onExtractionComplete: $e")
                }
            }

        }
        extractor.extract(url)

    }
    private fun setButtonsQuality(videoTitle : String, ytFile: YtFile){
        var fileName = ""
        val btnText = if(ytFile.format.height == -1) "Audio" +
                ytFile.format.audioBitrate + "kbits/s"
        else "${ytFile.format.height}" + " p"
        val btn  = Button(this)
        btn.text = btnText
        btn.setOnClickListener {
            if(videoTitle.length > 55) {
                 fileName = videoTitle.substring(0,55) + "." + ytFile.format.ext
            }else{
                  fileName = videoTitle + "." + ytFile.format.ext;
            }
            fileName = fileName.replace("[\\\\><\"|*?%:#/]",fileName)
            downloadFromUrl(ytFile.url,videoTitle,fileName)
            finish()
        }
        _binding.mainLayout.addView(btn)
    }
    private fun downloadFromUrl(url : String, title : String, fileName : String){
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setTitle(title)

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName)
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}