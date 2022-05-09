package hu.bme.vik.tbs.zooanimalapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.vik.tbs.zooanimalapp.ui.details.AnimalDetails
import hu.bme.vik.tbs.zooanimalapp.ui.details.DetailViewModel
import hu.bme.vik.tbs.zooanimalapp.ui.main.MainActivity
import hu.bme.vik.tbs.zooanimalapp.ui.theme.ZooAnimalAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityAnimalDetailsTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var activity: MainActivity

    @Before
    fun init() {
        composeTestRule.activityRule.scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun posterDetailsShibaInuLoadingTest() {
        composeTestRule.setContent {
            ZooAnimalAppTheme {

                val viewModel = hiltViewModel<DetailViewModel>()
                viewModel.loadAnimalById(0)

                AnimalDetails(
                    animalId = 0,
                    viewModel = viewModel
                )
            }
        }

        composeTestRule
            .onNodeWithText("Shiba Inu", ignoreCase = true)
            .assertIsDisplayed()
    }
}