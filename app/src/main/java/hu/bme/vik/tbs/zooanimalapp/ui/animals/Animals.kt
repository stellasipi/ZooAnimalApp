package hu.bme.vik.tbs.zooanimalapp.ui.animals

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import hu.bme.vik.tbs.zooanimalapp.R
import hu.bme.vik.tbs.zooanimalapp.extensions.visible
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import hu.bme.vik.tbs.zooanimalapp.ui.main.MainViewModel
import hu.bme.vik.tbs.zooanimalapp.ui.theme.Grey500
import hu.bme.vik.tbs.zooanimalapp.ui.theme.White

@Composable
fun Animals(
    viewModel: MainViewModel,
    selectAnimal: (Long) -> Unit
) {
    val animals: List<Animal> by viewModel.animalList.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading
    val selectedTab = ZooHomeTab.getTabFromResource(viewModel.selectedTab.value)
    val tabs = ZooHomeTab.values()

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { AnimalAppBar() },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.Gray,
                    modifier = Modifier
                        .navigationBarsHeight(56.dp)
                ) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                            label = { Text(text = stringResource(tab.title), color = Color.White) },
                            selected = tab == selectedTab,
                            onClick = { viewModel.selectTab(tab.title) },
                            selectedContentColor = LocalContentColor.current,
                            unselectedContentColor = LocalContentColor.current,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Crossfade(selectedTab) { destination ->
                when (destination) {
                    ZooHomeTab.HOME -> RadioAnimals(modifier, animals, selectAnimal)
                }
            }
        }
        CircularProgressIndicator(
            modifier = Modifier
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .visible(isLoading)
        )
    }
}

@Preview
@Composable
private fun AnimalAppBar() {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Color.Gray,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

enum class ZooHomeTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.menu_home, Icons.Filled.Home);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): ZooHomeTab {
            return when (resource) {
                R.string.menu_home -> HOME
                else -> HOME
            }
        }
    }
}