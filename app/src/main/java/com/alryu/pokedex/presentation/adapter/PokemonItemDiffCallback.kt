package com.alryu.pokedex.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alryu.pokedex.domain.Pokemon

class PokemonItemDiffCallback  : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem == newItem
}