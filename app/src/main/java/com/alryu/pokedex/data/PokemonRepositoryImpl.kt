package com.alryu.pokedex.data

import android.util.Log
import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonDetails
import com.alryu.pokedex.domain.PokemonRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl : PokemonRepository {
    private val api: PokedexApiService = createPokedexApiService() //TODO: use DI

    override fun getPokemonList(callback: PokemonRepository.ApiCallback<List<Pokemon>>) {
        api.getList().enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                val pokemonListResponse = response.body()

                if (response.isSuccessful && pokemonListResponse != null) {

                    val pokemonList = pokemonListResponse.results.map { pokemonPartialInfoDto ->
                        Pokemon(pokemonPartialInfoDto.id, pokemonPartialInfoDto.name, pokemonPartialInfoDto.imageUrl)
                    }

                    callback.onSuccess(pokemonList)
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                callback.onError()
            }
        })
    }

    override fun getPokemonById(id: String, callback: PokemonRepository.ApiCallback<PokemonDetails>) {
        api.getInfo(id).enqueue(object : Callback<PokemonInfoResponse> {
            override fun onResponse(call: Call<PokemonInfoResponse>, response: Response<PokemonInfoResponse>) {
                val pokemonInfo = response.body()

                if (response.isSuccessful && pokemonInfo != null) {
                    Log.i("pokemonInfo", "${pokemonInfo}")
                    val abilities = pokemonInfo.abilities.map {
                        it.ability.name
                    }

                    Log.i("abilities", "${abilities}")

                    val pokemonDetails = PokemonDetails(
                        pokemonInfo.id, pokemonInfo.name, pokemonInfo.height, pokemonInfo.weight, pokemonInfo.imageLargeUrl, abilities
                    )

                    callback.onSuccess(pokemonDetails)
                } else {
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable) {
                callback.onError()
            }
        })
    }
}