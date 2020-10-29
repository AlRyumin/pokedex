package com.alryu.pokedex.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alryu.pokedex.R
import com.alryu.pokedex.data.PokemonRepositoryImpl
import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonRepository
import com.alryu.pokedex.presentation.adapter.PokemonListAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {
    private val adapter = PokemonListAdapter()

    lateinit var repository: PokemonRepository

    val viewModel by viewModels<PokemonListViewModel>(
        factoryProducer = { PokemonListViewModelFactory(repository) }
    )

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


        recyclerView.adapter = adapter

        adapter.pokemonOnClickListener = object : PokemonListAdapter.PokemonItemOnClickListener {
            override fun onClicked(id: String) {
                val action =
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        id
                    )
                findNavController().navigate(action)
            }

        }

        initView()

        viewModel.loadData()
    }

    fun initView() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                loadBackground.visibility = View.VISIBLE
                loadingView.visibility = View.VISIBLE

            } else {
                loadBackground.visibility = View.GONE
                loadingView.visibility = View.GONE
            }
        })

        viewModel.isErrorLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                showError()
            } else {
                hideError()
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

        reloadButton.setOnClickListener {
            viewModel.loadData()
        }
    }

    fun showError() {
        errorView.visibility = View.VISIBLE
        reloadButton.visibility = View.VISIBLE
    }

    fun hideError() {
        errorView.visibility = View.GONE
        reloadButton.visibility = View.GONE
    }

    fun setData(data: List<Pokemon>) {
        adapter.submitList(data)
    }
}