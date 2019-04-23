/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.allinone.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.skydoves.allinone.R
import com.skydoves.needs.Needs
import com.skydoves.needs.NeedsAnimation
import com.skydoves.needs.NeedsItem
import com.skydoves.needs.createNeeds
import com.skydoves.needs.needsItemTheme
import com.skydoves.needs.needsTheme
import com.skydoves.needs.textForm

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
      addNeedsItem(
        NeedsItem(
          ContextCompat.getDrawable(context, R.drawable.ic_sdcard),
          "SD Card", context.getString(R.string.needs_required), context.getString(R.string.needs_sdcard)
        )
      )
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
