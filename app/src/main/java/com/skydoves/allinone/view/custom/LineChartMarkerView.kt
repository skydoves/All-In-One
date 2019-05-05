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

import android.annotation.SuppressLint
import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.layout_line_chart_marker.view.*

@SuppressLint("ViewConstructor")
class LineChartMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

  @SuppressLint("SetTextI18n")
  override fun refreshContent(e: Entry, highlight: Highlight) {
    if (e is CandleEntry) {
      content.text = Utils.formatNumber(e.high, 0, true) + "ml"
    } else {
      content.text = Utils.formatNumber(e.`val`, 0, true) + "ml"
    }
  }

  override fun getXOffset(xpos: Float): Int {
    return -(width / 2)
  }

  override fun getYOffset(ypos: Float): Int {
    return -height
  }
}
