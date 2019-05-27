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

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.skydoves.allinone.R

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.inVisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.isVisible(): Boolean {
  return visibility == View.VISIBLE
}

fun View.fadeIn() {
  if (alpha == 1f) {
    this.alpha = 0.99f
    val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
    this.startAnimation(fadeIn)
    fadeIn.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationRepeat(p0: Animation?) = Unit
      override fun onAnimationStart(p0: Animation?) = Unit
      override fun onAnimationEnd(p0: Animation?) {
        alpha = 1f
        visible()
      }
    })
  }
}

fun View.fadeOut() {
  if (alpha == 1f) {
    this.alpha = 0.99f
    val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
    this.startAnimation(fadeOut)
    fadeOut.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationRepeat(p0: Animation?) = Unit
      override fun onAnimationStart(p0: Animation?) = Unit
      override fun onAnimationEnd(p0: Animation?) {
        alpha = 1f
        gone()
      }
    })
  }
}
