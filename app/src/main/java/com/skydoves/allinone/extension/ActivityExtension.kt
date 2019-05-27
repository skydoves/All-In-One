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

package com.skydoves.allinone.extension

import android.app.Activity
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.content.ContextCompat
import com.skydoves.allinone.R

fun Activity.checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun Activity.circularRevealedAtCenter(view: View) {
  val cx = (view.left + view.right) / 2
  val cy = (view.top + view.bottom) / 2
  val finalRadius = Math.max(view.width, view.height)

  if (checkIsMaterialVersion() && view.isAttachedToWindow) {
    val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
    view.visible()
    view.setBackgroundColor(ContextCompat.getColor(this, R.color.background))
    anim.duration = 550
    anim.start()
  }
}

fun Activity.textWatcher(afterTextChanged: () -> Unit): TextWatcher {
  return object : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
      afterTextChanged()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
  }
}

fun Activity.overridePendingUp() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_no_change)
}

fun Activity.overridePendingDown() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    overridePendingTransition(R.anim.slide_no_change, R.anim.slide_out_down)
}
