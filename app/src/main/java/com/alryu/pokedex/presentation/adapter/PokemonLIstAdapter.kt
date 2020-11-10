package com.alryu.pokedex.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alryu.pokedex.R
import com.alryu.pokedex.domain.Pokemon

class PokemonListAdapter: ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(PokemonItemDiffCallback()) {

    var pokemonOnClickListener: PokemonItemOnClickListener? = null

    interface PokemonItemOnClickListener {
        fun onClicked(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindTo(getItem(position), pokemonOnClickListener)
    }

    class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindTo(pokemon: Pokemon, pokemonOnClickListener: PokemonItemOnClickListener?){
            itemView.setOnClickListener {
                pokemonOnClickListener?.onClicked(pokemon.id)
            }

            name.text = pokemon.name

            image.load(pokemon.imageUrl)
        }
    }

}
