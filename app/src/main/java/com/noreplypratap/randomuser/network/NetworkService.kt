package com.noreplypratap.randomuser.network

import com.noreplypratap.randomuser.model.RandomJokes
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/random_joke")
    suspend fun getRandomJokesFromApi() : Response<RandomJokes>

}