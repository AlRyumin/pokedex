package com.alryu.pokedex.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alryu.pokedex.PokemonRepositoryImpl
import com.alryu.pokedex.domain.Pokemon
import com.alryu.pokedex.domain.PokemonRepository
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private val repository: PokemonRepository = PokemonRepositoryImpl()

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean> = _isErrorLiveData

    private val _contentLiveData = MutableLiveData<List<Pokemon>>()
    val contentLiveData: LiveData<List<Pokemon>> = _contentLiveData

    fun loadData() {
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false
        _contentLiveData.value = emptyList() //bad

        Handler(Looper.getMainLooper()).postDelayed({
            if (Random.nextInt() % 10  == 0) {
                _isLoadingLiveData.value = false
                _isErrorLiveData.value = true
                _contentLiveData.value = emptyList() //bad
            } else {
                val data = repository.getPokemonList()
                _isLoadingLiveData.value = false
                _isErrorLiveData.value = false
                _contentLiveData.postValue(data)
            }
        }, 3000)
    }

}