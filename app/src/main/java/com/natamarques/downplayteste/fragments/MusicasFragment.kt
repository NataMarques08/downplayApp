package com.natamarques.downplayteste.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.downplayteste.R
import com.example.downplayteste.databinding.FragmentMusicasBinding


class MusicasFragment : Fragment() {


    private lateinit var _binding : FragmentMusicasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicasBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return _binding.root
    }


}