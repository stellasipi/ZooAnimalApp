package hu.bme.vik.tbs.zooanimalapp.network

import com.skydoves.sandwich.ApiResponse
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalService {

    @GET("animals/rand")
    suspend fun fetchRandomAnimal(): ApiResponse<Animal>

    @GET("animals/rand/{number}")
    suspend fun fetchSpecificAmountOfAnimals(@Path("number") number: Int): ApiResponse<List<Animal>>

}