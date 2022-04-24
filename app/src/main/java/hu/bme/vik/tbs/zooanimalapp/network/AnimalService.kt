package hu.bme.vik.tbs.zooanimalapp.network

import hu.bme.vik.tbs.zooanimalapp.model.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalService {

    @GET("animals/rand")
    suspend fun fetchRandomAnimal(): Call<Animal>

    @GET("animals/rand/{number}")
    suspend fun fetchSpecificAmountOfAnimals(@Path("id") number: Int): Call<List<Animal>>

}