package hu.bme.vik.tbs.zooanimalapp.ui.animals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import hu.bme.vik.tbs.zooanimalapp.ui.theme.ZooAnimalAppTheme
import hu.bme.vik.tbs.zooanimalapp.utils.NetworkImage

class RadioAnimals {
}

@Composable
fun RadioAnimals(
    modifier: Modifier = Modifier,
    animals: List<Animal>,
    selectAnimal: (Long) -> Unit = {},
) {
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = animals,
                itemContent = { animal ->
                    RadioAnimal(
                        animal = animal,
                        selectAnimal = selectAnimal
                    )
                }
            )
        }
    }
}

@Composable
private fun RadioAnimal(
    modifier: Modifier = Modifier,
    animal: Animal,
    selectAnimal: (Long) -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = { selectAnimal(animal.id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(8.dp)
        ) {
            val (image, title, content) = createRefs()

            NetworkImage(
                modifier = Modifier
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        end.linkTo(title.start)
                    }
                    .height(64.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp)),
                url = animal.image_link
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(image.end)
                    }
                    .padding(horizontal = 12.dp),
                text = animal.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )

            Text(
                modifier = Modifier
                    .constrainAs(content) {
                        start.linkTo(image.end)
                        top.linkTo(title.bottom)
                    }
                    .padding(start = 12.dp, top = 4.dp),
                text = animal.latin_name,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Composable
@Preview(name = "RadioAnimal Light")
private fun RadioPosterPreviewLight() {
    ZooAnimalAppTheme(darkTheme = false) {
        RadioAnimal(
            animal = Animal.mock(),
            selectAnimal = { }
        )
    }
}

@Composable
@Preview(name = "RadioAnimal Dark")
private fun RadioPosterPreviewDark() {
    ZooAnimalAppTheme(darkTheme = true) {
        RadioAnimal(
            animal = Animal.mock(),
            selectAnimal = { }
        )
    }
}