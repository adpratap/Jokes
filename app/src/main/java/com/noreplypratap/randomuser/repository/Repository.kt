package com.noreplypratap.randomuser.repository

import com.noreplypratap.randomuser.model.RandomJokes
import com.noreplypratap.randomuser.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor( private val networkService: NetworkService)  {

    suspend fun getRandomJokes() : Response<RandomJokes> {
            return networkService.getRandomJokesFromApi()
    }

}