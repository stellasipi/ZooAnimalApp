package hu.bme.vik.tbs.zooanimalapp.model

data class Animal(
    val id: Int,
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
)
