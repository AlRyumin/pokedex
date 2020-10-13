package com.alryu.pokedex

import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonRepository

class PokemonRepositoryImpl: PokemonRepository{
    override fun getPokemonList(): List<Pokemon> {
            return listOf(
                Pokemon("1","Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
                Pokemon("4","Charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"),
                Pokemon("18","Pidgeot", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png"),
                Pokemon("25","Pikachu", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"),
                Pokemon("54","Psyduck", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/54.png")
            )
    }
}