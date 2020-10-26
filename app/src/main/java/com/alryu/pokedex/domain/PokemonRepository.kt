package com.alryu.pokedex.domain

interface PokemonRepository {
    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError()
    }

    fun getPokemonList(callback: ApiCallback<List<Pokemon>>)

    fun getPokemonById(id: String, callback: ApiCallback<PokemonDetails>)
}