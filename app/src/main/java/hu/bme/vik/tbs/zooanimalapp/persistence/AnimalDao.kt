package hu.bme.vik.tbs.zooanimalapp.persistence

import androidx.room.*
import hu.bme.vik.tbs.zooanimalapp.model.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM Animal")
    fun getAllAnimals(): List<Animal>

    @Query("SELECT * FROM Animal WHERE id = :id_")
    fun getAnimalById(id_: Long): Animal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimalList(animals: List<Animal>)

    @Delete
    suspend fun deleteAnimal(animal: Animal)
}