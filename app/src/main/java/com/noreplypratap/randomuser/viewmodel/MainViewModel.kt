package com.noreplypratap.randomuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.randomuser.model.RandomJokes
import com.noreplypratap.randomuser.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _responseLiveData = MutableLiveData<RandomJokes>()
    val responseLiveData : LiveData<RandomJokes>
    get() = _responseLiveData

    private val _responseErrorData = MutableLiveData<String>()
    val responseErrorData : LiveData<String>
        get() = _responseErrorData

    fun getJokes() = viewModelScope.launch {
        repository.getRandomJokes().let {
            if (it.isSuccessful){
                    _responseLiveData.postValue(it.body())
            }else{
                    _responseErrorData.postValue(it.errorBody().toString())
            }
        }
    }
}