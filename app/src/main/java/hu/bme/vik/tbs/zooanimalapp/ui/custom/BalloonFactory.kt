package hu.bme.vik.tbs.zooanimalapp.ui.custom

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*
import hu.bme.vik.tbs.zooanimalapp.R

object BalloonFactory {

    fun create(context: Context, title: String, lifecycle: LifecycleOwner?): Balloon {
        val textForm = textForm(context) {
            setText(title)
            setTextSize(15f)
            setTextColorResource(R.color.white)
        }

        return createBalloon(context) {
            setArrowSize(10)
            setPadding(10)
            setCornerRadius(8f)
            setElevation(4)
            setTextForm(textForm)
            setArrowPosition(0.5f)
            setArrowOrientation(ArrowOrientation.TOP)
            setBackgroundColorResource(R.color.purple_500)
            setDismissWhenClicked(true)
            setDismissWhenShowAgain(true)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycle)
            build()
        }
    }
}