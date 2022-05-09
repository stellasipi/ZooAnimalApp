package hu.bme.vik.tbs.zooanimalapp.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val animalIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    val animalDetailsFlow = animalIdSharedFlow.flatMapLatest {
        detailRepository.getPosterById(it)
    }

    init {}

    fun loadAnimalById(id: Long) = animalIdSharedFlow.tryEmit(id)
}