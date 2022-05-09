package hu.bme.vik.tbs.zooanimalapp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NetworkUrlPreviewProvider : PreviewParameterProvider<String> {
    override val count: Int
        get() = super.count
    override val values: Sequence<String>
        get() = sequenceOf("https://upload.wikimedia.org/wikipedia/commons/7/73/Lion_waiting_in_Namibia.jpg")
}