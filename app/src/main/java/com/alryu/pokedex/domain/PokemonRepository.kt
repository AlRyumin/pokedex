package com.alryu.pokedex.domain

interface PokemonRepository {
    fun getPokemonList(): List<Pokemon>
}