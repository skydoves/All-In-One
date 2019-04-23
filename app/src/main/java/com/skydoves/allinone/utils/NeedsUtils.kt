package com.skydoves.allinone.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.skydoves.allinone.R
import com.skydoves.needs.*
import org.jetbrains.anko.toast

object NeedsUtils {

    fun getNeedsPopup(context: Context, owner: LifecycleOwner): Needs {
        val titleForm = textForm(context) {
            textSize = 18
            textStyle = Typeface.BOLD
            textColor = ContextCompat.getColor(context, R.color.black)
        }

        val theme = needsTheme(context) {
            backgroundColor = ContextCompat.getColor(context, R.color.background)
            titleTextForm = textForm(context) {
                textSize = 18
                textColor = ContextCompat.getColor(context, R.color.white)
            }
            descriptionTextForm = textForm(context) {
                textSize = 12
                textColor = ContextCompat.getColor(context, R.color.description)
            }
        }

        val itemTheme = needsItemTheme(context) {
            backgroundColor = ContextCompat.getColor(context, R.color.background)
            titleTextForm = textForm(context) {
                textColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                textSize = 16
            }
            descriptionTextForm = textForm(context) {
                textColor = ContextCompat.getColor(context, R.color.description)
            }
        }

        return createNeeds(context) {
            titleIcon = ContextCompat.getDrawable(context, R.drawable.ic_drop)
            title = context.getString(R.string.needs_title)
            titleTextForm = titleForm
            addNeedsItem(NeedsItem(ContextCompat.getDrawable(context, R.drawable.ic_sdcard),
                "SD Card", context.getString(R.string.needs_required), context.getString(R.string.needs_sdcard)))
            description = context.getString(R.string.needs_sdcard_description)
            confirm = context.getString(R.string.confirm)
            backgroundAlpha = 0.6f
            lifecycleOwner = owner
            needsTheme = theme
            needsItemTheme = itemTheme
            needsAnimation = NeedsAnimation.CIRCULAR
            preferenceName = "permission"
        }
    }
}