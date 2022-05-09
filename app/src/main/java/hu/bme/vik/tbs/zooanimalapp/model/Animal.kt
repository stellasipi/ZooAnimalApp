package hu.bme.vik.tbs.zooanimalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import javax.annotation.concurrent.Immutable

@Entity
@Immutable
//@JsonClass(generateAdapter = false)
data class Animal(
    @PrimaryKey @Json(name="id")
    val id: Long,
    @Json(name="name")
    val name: String,
    @Json(name="latin_name")
    val latin_name: String,
    @Json(name="image_link")
    val image_link: String,
    @Json(name="animal_type")
    val animal_type: String,
    @Json(name="lifespan")
    val lifespan: Int,
    @Json(name="habitat")
    val habitat: String,
    @Json(name="diet")
    val diet: String,
    @Json(name="geo_range")
    val geo_range: String,
    @Json(name="active_time")
    val active_time: String,
    @Json(name="length_min")
    val length_min: Double,
    @Json(name="length_max")
    val length_max: Double,
    @Json(name="weight_min")
    val weight_min: Int,
    @Json(name="weight_max")
    val weight_max: Int
) {
    companion object {
        fun mock() = Animal(
            id = 0,
            name = "Shiba inu",
            latin_name = "Shiba inu",
            image_link = "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg",
            animal_type = "Mammal",
            lifespan = 14,
            habitat = "Pet",
            diet = "Pet food",
            geo_range = "Japan",
            active_time = "Diurnal",
            length_max = 43.0,
            length_min = 33.0,
            weight_max = 10,
            weight_min = 8
        )
    }
}
