package hu.bme.vik.tbs.zooanimalapp.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    // TODO
}