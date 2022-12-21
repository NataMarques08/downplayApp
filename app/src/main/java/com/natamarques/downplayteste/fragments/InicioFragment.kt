package com.natamarques.downplayteste.fragments



import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView.inflate
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.downplayteste.databinding.FragmentInicioBinding
import com.natamarques.downplayteste.helper.Constants
import com.natamarques.downplayteste.model_search._YoutubeList
import com.natamarques.downplayteste.videoAdapterYT.VideoYtAdapter
import com.natamarques.downplayteste.viewmodel.MainViewModel



class InicioFragment : Fragment() {

    private lateinit var _binding: FragmentInicioBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var videoYtAdapter: VideoYtAdapter





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        initRecyclerView("")



        _binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("TEXTO1", "onQueryTextSubmit: $query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                initRecyclerView(newText.toString())
                return false
            }

        })

        return _binding.root
    }



    private fun initRecyclerView(q : String) {
        val lista = mutableListOf<_YoutubeList>()
        videoYtAdapter = VideoYtAdapter()

            _binding.recyclerViewYtVideos.apply {
                adapter = videoYtAdapter
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
            }
            viewModel.responseYT.observe(viewLifecycleOwner) {

                for (position in 0 until it.items.size) lista.add(it)
                videoYtAdapter.submitList(lista)
            }
        viewModel.getVideos(q)


    }









}