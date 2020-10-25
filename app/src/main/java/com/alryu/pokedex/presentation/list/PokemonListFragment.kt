package com.alryu.pokedex.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alryu.pokedex.PokemonRepositoryImpl
import com.alryu.pokedex.R
import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonRepository
import com.alryu.pokedex.presentation.adapter.PokemonListAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment: Fragment(R.layout.fragment_pokemon_list) {
    private val adapter = PokemonListAdapter()

    lateinit var repository: PokemonRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        repository = PokemonRepositoryImpl()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<PokemonListViewModel>(
            factoryProducer = { PokemonListViewModelFactory(repository) }
        )

        recyclerView.adapter = adapter

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer {
            if(it){
                loadBackground.visibility = View.VISIBLE
                loadingView.visibility = View.VISIBLE

            } else {
                loadBackground.visibility = View.GONE
                loadingView.visibility = View.GONE
            }
        })

        viewModel.isErrorLiveData.observe(viewLifecycleOwner, Observer {
            errorView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.contentLiveData.observe(viewLifecycleOwner, Observer { data ->
            recyclerView.visibility = if (data.isNotEmpty()) { //bad
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