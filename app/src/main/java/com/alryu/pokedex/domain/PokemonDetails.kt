package com.alryu.pokedex.domain

data class PokemonDetails(val id: String, val name: String,  val imageUrl: String, val abilities: List<String>)