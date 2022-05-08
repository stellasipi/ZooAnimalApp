package hu.bme.vik.tbs.zooanimalapp.ui.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.suspendOnSuccess
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import hu.bme.vik.tbs.zooanimalapp.network.AnimalService
import hu.bme.vik.tbs.zooanimalapp.persistence.AnimalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val animalService: AnimalService,
    private val animalDao: AnimalDao
) {
    init {
    }

    @WorkerThread
    fun loadAnimals(onStart: () -> Unit, onCompletion: () -> Unit) =
        flow {
            val posters: List<Animal> = animalDao.getAllAnimals()
            if (posters.isEmpty()) {
                animalService.fetchSpecificAmountOfAnimals(10)
                    .suspendOnSuccess {
                        animalDao.insertAnimalList(data)
                        emit(data)
                    }
//                animalService.fetchSpecificAmountOfAnimals(10)
//                    .enqueue(object : Callback<List<Animal>> {
//                        override fun onResponse(
//                            call: Call<List<Animal>>,
//                            response: Response<List<Animal>>
//                        ) {
//                            if (response.body() != null) {
//                                //animalDao.insertAnimalList(response.body()!!)
//                                //emit(response.body())
//                            }
//                        }
//
//                        override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
//                        }
//                    })
            } else {
                emit(posters)
            }
        }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}