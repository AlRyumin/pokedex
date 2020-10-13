package com.alryu.pokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alryu.pokedex.R
import com.alryu.pokedex.presentation.adapter.PokemonListAdapter
import com.alryu.pokedex.domain.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter = PokemonListAdapter()
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonListRV.adapter = adapter

        viewModel.isLoadingLiveData.observe(this, Observer {
            loadingView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.isErrorLiveData.observe(this, Observer {
            errorView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.contentLiveData.observe(this, Observer { data ->
            pokemonListRV.visibility = if (data.isNotEmpty()) { //bad
                View.VISIBLE
            } else {
                View.GONE
            }
            setData(data)
        })

        viewModel.loadData()
    }

    fun setData(data: List<Pokemon>) {
        adapter.submitList(data)
    }

}