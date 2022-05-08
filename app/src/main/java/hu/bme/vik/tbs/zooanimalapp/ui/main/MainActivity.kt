package hu.bme.vik.tbs.zooanimalapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.vik.tbs.zooanimalapp.ui.root.RootViewModel
import hu.bme.vik.tbs.zooanimalapp.ui.theme.ZooAnimalAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @VisibleForTesting
    internal val viewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {
                ZooAnimalAppTheme {
                    ZooAnimalMainScreen()
                }
            }
        }
    }
}