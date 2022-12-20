package com.natamarques.downplayteste.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natamarques.downplayteste.helper.Constants
import com.natamarques.downplayteste.model_search._YoutubeList
import com.natamarques.downplayteste.repository.RepositoryYT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MainViewModel
@Inject
constructor(private val repositoryYT: RepositoryYT) : ViewModel(){
    private val _response = MutableLiveData<_YoutubeList>()
    val responseYT : LiveData<_YoutubeList>
    get() = _response

    init {
        getVideos("")
    }

     fun getVideos(q : String) = viewModelScope.launch{
            repositoryYT.getVideos(q).let { response ->
                if(response.isSuccessful){
                    _response.value = response.body()
                }else{
                    Log.d(Constants.TAG, "getVideos: ${response.message()}")
                }
            }
    }
   }


