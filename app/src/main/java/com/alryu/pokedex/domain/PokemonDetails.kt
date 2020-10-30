package com.alryu.pokedex.domain

data class PokemonDetails(
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val imageUrl: String,
    val abilities: List<String>)