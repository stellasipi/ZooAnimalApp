package hu.bme.vik.tbs.zooanimalapp.persistence

import hu.bme.vik.tbs.zooanimalapp.model.Animal
import hu.bme.vik.tbs.zooanimalapp.network.LocalDatabase
import hu.bme.vik.tbs.zooanimalapp.utils.MockTestUtil.mockAnimalList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PosterDaoTest : LocalDatabase() {

    private lateinit var animalDao: AnimalDao

    @Before
    fun init() {
        animalDao = db.animalDao()
    }

    @Test
    fun insertAndLoadPosterListTest() = runBlocking {
        val mockDataList = mockAnimalList()
        animalDao.insertAnimalList(mockDataList)

        val loadFromDB = animalDao.getAllAnimals()
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = Animal.mock()
        MatcherAssert.assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}