package hu.bme.vik.tbs.zooanimalapp.ui.main

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.vik.tbs.zooanimalapp.model.Animal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    val animalList: Flow<List<Animal>> =
        mainRepository.loadAnimals(
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false }
        )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0) // TODO ez nem fog kelleni
    val selectedTab: State<Int> get() = _selectedTab

    init {}

    fun selectTab(@StringRes tab: Int) { // TODO ez nem fog kelleni
        _selectedTab.value = tab
    }
}