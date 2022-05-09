package hu.bme.vik.tbs.zooanimalapp.ui.details

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.palette.BitmapPalette
import hu.bme.vik.tbs.zooanimalapp.extensions.paletteColorList
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import hu.bme.vik.tbs.zooanimalapp.ui.custom.ImageBalloonAnchor
import hu.bme.vik.tbs.zooanimalapp.utils.NetworkImage

class AnimalDetails {
}

@Composable
fun AnimalDetails(
    animalId: Long,
    viewModel: DetailViewModel,
    pressOnBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = animalId) {
        viewModel.loadAnimalById(animalId)
    }

    val details: Animal? by viewModel.animalDetailsFlow.collectAsState(initial = null)
    details?.let { poster ->
        AnimalDetailsBody(poster, pressOnBack)
    }
}

@Composable
private fun AnimalDetailsBody(
    animal: Animal,
    pressOnBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        var palette by remember { mutableStateOf<Palette?>(null) }

        ConstraintLayout {
            val (arrow, image, paletteRow, title, content, gifTitle, gif) = createRefs()

            NetworkImage(
                url = animal.image_link,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
                circularRevealEnabled = true,
                bitmapPalette = BitmapPalette {
                    palette = it
                }
            )

            ColorPalettes(
                palette = palette,
                modifier = Modifier
                    .constrainAs(paletteRow) {
                        top.linkTo(image.bottom)
                    }
            )

            Text(
                text = animal.name,
                style = MaterialTheme.typography.h4,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(paletteRow.bottom)
                    }
                    .padding(start = 16.dp, top = 12.dp)
            )

            Text(
                text = "Type: "+animal.animal_type,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(title.bottom)
                    }
                    .padding(16.dp)
            )

//            Text(
//                text = "Latin name: "+animal.latin_name,
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(30.dp)
//            )
//
//            Text(
//                text = "Active time: "+animal.active_time,
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Length: "+animal.length_min+" - "+animal.length_max+" m",
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Weight: "+animal.weight_min+" - "+animal.weight_max+" kg",
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Lifespan: "+animal.lifespan+" year(s)",
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Habitat: "+animal.habitat,
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Diet: "+animal.diet,
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )
//
//            Text(
//                text = "Geo range: "+animal.geo_range,
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .constrainAs(content) {
//                        top.linkTo(title.bottom)
//                    }
//                    .padding(16.dp)
//            )

            ImageBalloonAnchor(
                reference = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f),
                content = animal.name,
                onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
            )

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(arrow) {
                        top.linkTo(parent.top)
                    }
                    .padding(12.dp)
                    .clickable(onClick = { pressOnBack() })
            )
        }
    }
}

@Composable
private fun ColorPalettes(
    palette: Palette?,
    modifier: Modifier
) {
    val colorList: List<Int> = palette.paletteColorList()
    LazyRow(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(colorList) { color ->
            Crossfade(
                targetState = color,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(45.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color(it))
                        .fillMaxSize()
                )
            }
        }
    }
}