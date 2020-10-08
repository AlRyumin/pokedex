package com.alryu.pokedex.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alryu.pokedex.model.Pokemon

class PokemonItemDiffCallback  : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem == newItem
}