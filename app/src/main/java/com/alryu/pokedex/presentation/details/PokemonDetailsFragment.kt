package com.alryu.pokedex.presentation.details

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.alryu.pokedex.R
import com.alryu.pokedex.domain.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import kotlinx.android.synthetic.main.fragment_pokemon_list.errorView
import kotlinx.android.synthetic.main.item_pokemon.image
import kotlinx.android.synthetic.main.item_pokemon.name
import kotlin.random.Random


const val PARAM_POKEMON_ID = "param.pokemon.id"

class PokemonDetailsFragment : Fragment() {
    val args: PokemonDetailsFragmentArgs by navArgs()
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<PokemonDetailsViewModel>()
        val pokemonId = args.id ?: "1"

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
        val hp = Random.nextInt(20, 80)
        name.text = data.name

        if(data.abilities.isEmpty()){
            abilities.visibility = View.GONE
            abilitiesList.visibility = View.GONE
        } else {
            arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
            data.abilities.forEach{
                arrayAdapter.add(it)
            }
            abilitiesList.adapter = arrayAdapter
        }

        Picasso.get()
            .load(data.imageUrl)
            .into(image)

        hpProgress(hp)
    }

    fun hpProgress(max: Int){
        val animator = ObjectAnimator.ofInt(progressHp, "progress", 0, max)
        animator.interpolator = DecelerateInterpolator()
        animator.duration = 1000
        animator.start()
    }
}