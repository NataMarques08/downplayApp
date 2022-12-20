package com.natamarques.downplayteste.ui



import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.downplayteste.R
import com.example.downplayteste.databinding.ActivityMainBinding
import com.natamarques.downplayteste.fragments.InicioFragment
import com.natamarques.downplayteste.fragments.MusicasFragment
import com.natamarques.downplayteste.fragments.VideoFragment
import com.natamarques.downplayteste.helper.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("RESPOSTA", "onCreate: ${Constants.query} ")

        if(savedInstanceState == null) initFragments(InicioFragment())
        navControllerButtons()

    }




    private fun navControllerButtons() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.inicio -> initFragments(InicioFragment())
                R.id.musicas -> initFragments(MusicasFragment())
                R.id.videos -> initFragments(VideoFragment())
            }
           true
        }

    }


    private fun initFragments(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.frameLayout,fragment)
            addToBackStack(null)
        }

    }


}


