package hu.bme.vik.tbs.zooanimalapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.vik.tbs.zooanimalapp.model.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Insert
    suspend fun insertAnimal(animal: Animal): Long

    @Insert
    suspend fun insertAnimal(vararg animal: Animal): List<Long>

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    @Query("DELETE FROM animal")
    suspend fun deleteAllAnimal()
}