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

package com.skydoves.allinone.view.custom

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.displaySize
import com.skydoves.allinone.extension.dp2Px

@Suppress("unused")
class WaterSelectPopup(
  private val context: Context
) : LifecycleObserver {

  private val backgroundView: View
  private val backgroundWindow: PopupWindow
  private val bodyView: View
  private val bodyWindow: PopupWindow

  var isShowing = false
    private set

  init {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    this.backgroundView = inflater.inflate(R.layout.layout_background, null)
    this.backgroundWindow = PopupWindow(
      backgroundView,
      FrameLayout.LayoutParams.MATCH_PARENT,
      FrameLayout.LayoutParams.MATCH_PARENT
    )
    this.bodyView = inflater.inflate(R.layout.layout_item_select_popup, null)
    this.bodyWindow = PopupWindow(
      bodyView,
      context.displaySize().x - context.dp2Px(20) * 2,
      LinearLayout.LayoutParams.WRAP_CONTENT
    )
  }

  /** shows the popup menu to the center. */
  fun show(view: View) {
    if (!isShowing) {
      bodyWindow.animationStyle = R.style.Fade
      backgroundWindow.animationStyle = R.style.Fade
      backgroundWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
      bodyWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
      isShowing = true
    }
  }

  /** dismiss the popup menu. */
  fun dismiss() {
    if (isShowing) {
      backgroundWindow.dismiss()
      bodyWindow.dismiss()
      isShowing = false
    }
  }

  /** dismiss automatically when lifecycle owner is destroyed. */
  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun onDestroy() {
    dismiss()
  }
}
