package hu.bme.vik.tbs.zooanimalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
data class Animal(
    @PrimaryKey val id: Long,
    val name: String,
    val latinName: String,
    val imageLink: String,
    val animalType: String,
    val lifespan: Int,
    val habitat: String,
    val diet: String,
    val geoRange: String,
    val activeTime: String,
    val lengthMin: Double,
    val lengthMax: Double,
    val weightMin: Int,
    val weightMax: Int
) {
    companion object {
        fun mock() = Animal(
            id = 0,
            name = "Shiba inu",
            latinName = "Shiba inu",
            imageLink = "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg",
            animalType = "Mammal",
            lifespan = 14,
            habitat = "Pet",
            diet = "Pet food",
            geoRange = "Japan",
            activeTime = "Diurnal",
            lengthMax = 43.0,
            lengthMin = 33.0,
            weightMax = 10,
            weightMin = 8
        )
    }
}
