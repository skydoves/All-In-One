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
import android.graphics.Color
import android.util.TypedValue
import android.widget.RelativeLayout
import com.github.jorgecastillo.FillableLoader
import com.skydoves.allinone.R

object FillAbleLoaderUtils {

  fun getParams(context: Context): RelativeLayout.LayoutParams {
    val viewSize = context.resources.getDimensionPixelSize(R.dimen.fourthSampleViewSize)
    val params = RelativeLayout.LayoutParams(viewSize, viewSize)
    params.width =
      TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220f, context.resources.displayMetrics).toInt()
    params.height =
      TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 305f, context.resources.displayMetrics).toInt()
    params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
    return params
  }

  fun refreshPercentage(fillAbleLoader: FillableLoader, percent: Float) {
    fillAbleLoader.reset()
    fillAbleLoader.setSvgPath(FillAbleLoaderPaths.SVG_WATERDROP)
    fillAbleLoader.setFillColor(Color.WHITE)
    fillAbleLoader.setPercentage(percent)
    fillAbleLoader.start()
  }
}
