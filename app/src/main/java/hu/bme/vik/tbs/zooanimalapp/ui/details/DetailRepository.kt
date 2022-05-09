package hu.bme.vik.tbs.zooanimalapp.ui.details

import androidx.annotation.WorkerThread
import hu.bme.vik.tbs.zooanimalapp.persistence.AnimalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val animalDao: AnimalDao
) {
    @WorkerThread
    fun getPosterById(id: Long) = flow {
        val poster = animalDao.getAnimalById(id)
        emit(poster)
    }.flowOn(Dispatchers.IO)
}