package com.alryu.pokedex.presentation.list

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alryu.pokedex.data.PokemonRepositoryImpl
import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonRepository
import kotlin.random.Random

class PokemonListViewModel (
    val repository: PokemonRepository
) : ViewModel() {

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean> = _isErrorLiveData

    private val _contentLiveData = MutableLiveData<List<Pokemon>>()
    val contentLiveData: LiveData<List<Pokemon>> = _contentLiveData

    fun loadData() {
        showLoading()

        Handler().postDelayed({
            if (Random.nextInt() % 10  == 0) {
                showError()
            } else {
                repository.getPokemonList(object : PokemonRepository.ApiCallback<List<Pokemon>> {
                    override fun onSuccess(data: List<Pokemon>) {
                        showData(data)
                    }

                    override fun onError() {
                        showError()
                    }
                })
            }
        }, 500)
    }

    private fun showLoading() {
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false
        _contentLiveData.value = emptyList() //bad
    }

    private fun showData(data: List<Pokemon>) {
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = false
        _contentLiveData.postValue(data)
    }

    private fun showError() {
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = true
        _contentLiveData.value = emptyList() //bad
    }
}