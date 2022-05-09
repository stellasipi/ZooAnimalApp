package hu.bme.vik.tbs.zooanimalapp.ui.main

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.*
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
            println("FLOW")
            val animals: List<Animal> = animalDao.getAllAnimals()
            if (animals.isEmpty()) {
                println("EMPTY")
                animalService.fetchSpecificAmountOfAnimals(10)
                    .suspendOnError {
                        println("suspendOnError")
                        println(response)
                    }
                    .suspendOnException {
                        println("suspendOnException")
                        println(exception)
                    }
                    .suspendOnSuccess {
                        println("")
                        println("DATA:")
                        println(data)
                        animalDao.insertAnimalList(data)
                        emit(data)
                    }
            } else {
                println("ELSE  DATA:")
                println(animals)
                emit(animals)
            }
        }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}