package com.alryu.pokedex.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

interface PokedexApiService {

    @GET("pokemon")
    fun getList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Call<PokemonListResponse>


    @GET("pokemon/{name}")
    fun getInfo(@Path("name") name: String): Call<PokemonInfoResponse>
}

fun createPokedexApiService(): PokedexApiService{
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    return retrofit.create(PokedexApiService::class.java)
}