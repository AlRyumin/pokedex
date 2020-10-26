package com.alryu.pokedex.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alryu.pokedex.data.PokemonRepositoryImpl
import com.alryu.pokedex.domain.PokemonDetails
import com.alryu.pokedex.domain.PokemonRepository

class PokemonDetailsViewModel : ViewModel() {
    val repository: PokemonRepository = PokemonRepositoryImpl()

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean> = _isErrorLiveData

    private val _contentLiveData = MutableLiveData<PokemonDetails>()
    val contentLiveData: LiveData<PokemonDetails> = _contentLiveData

    fun loadData(id: String) {
        repository.getPokemonById(id, object: PokemonRepository.ApiCallback<PokemonDetails>{
            override fun onSuccess(data: PokemonDetails) {
                showData(data)
            }

            override fun onError() {
                showError()
            }
        })
    }

    fun showData(data: PokemonDetails) {
        _isErrorLiveData.value = false
        _contentLiveData.postValue(data)
    }

    fun showError() {
        _isErrorLiveData.value = true
        _contentLiveData.value = null
    }
}