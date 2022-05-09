package hu.bme.vik.tbs.zooanimalapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Grey500,
    primaryVariant = Grey700, //Grey700
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Grey500,
    primaryVariant = Grey700,
    secondary = Teal200
)

@Composable
fun ZooAnimalAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}