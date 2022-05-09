package hu.bme.vik.tbs.zooanimalapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.vik.tbs.zooanimalapp.model.Animal

@Database(entities = [Animal::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}