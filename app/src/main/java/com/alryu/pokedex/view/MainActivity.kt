package com.alryu.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alryu.pokedex.R
import com.alryu.pokedex.adapter.PokemonListAdapter
import com.alryu.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.pokemonListRV

class MainActivity : AppCompatActivity() {
    private val adapter = PokemonListAdapter()
    val pokemonList = getPockemonList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonListRV.adapter = adapter

        adapter.submitList(pokemonList)
    }

    private fun getPockemonList() : List<Pokemon>{
        //https://pokeapi.co/
        //https://bulbapedia.bulbagarden.net/wiki/Bulbasaur_(Pok%C3%A9mon)
        return listOf(
            Pokemon("1","Bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("4","Charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"),
            Pokemon("18","Pidgeot", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png"),
            Pokemon("25","Pikachu", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"),
            Pokemon("54","Psyduck", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/54.png")
        )
    }
}