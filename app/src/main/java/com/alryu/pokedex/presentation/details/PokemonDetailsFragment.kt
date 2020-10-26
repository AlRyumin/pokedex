package com.alryu.pokedex.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alryu.pokedex.R
import com.alryu.pokedex.domain.PokemonDetails
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.item_pokemon.*

const val PARAM_POKEMON_ID = "param.pokemon.id"

class PokemonDetailsFragment : Fragment() {
    companion object {
        fun newInstance(id: String): PokemonDetailsFragment {
            val fragment = PokemonDetailsFragment()
            val bundle = bundleOf(
                PARAM_POKEMON_ID to id
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<PokemonDetailsViewModel>()
        val pokemonId = requireArguments().getString(PARAM_POKEMON_ID) ?: "1"
        Log.i("PARAM_POKEMON_ID", "${pokemonId}")

        viewModel.isErrorLiveData.observe(viewLifecycleOwner, Observer {
            errorView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.contentLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (data == null) { //bad
                hideContent()
            } else {
                showContent()
                setData(data)
            }
        })

        viewModel.loadData(pokemonId)
    }

    fun hideContent(){
        image.visibility = View.GONE
        name.visibility = View.GONE
    }

    fun showContent(){
        image.visibility = View.VISIBLE
        name.visibility = View.VISIBLE
    }

    fun setData(data: PokemonDetails) {
        name.text = data.name
    }
}