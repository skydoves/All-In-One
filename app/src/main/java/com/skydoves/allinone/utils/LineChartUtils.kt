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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.skydoves.allinone.R
import com.skydoves.allinone.view.custom.LineChartMarkerView
import java.util.ArrayList

object LineChartUtils {

  fun setWaterDrinkLineChart(
    context: Context,
    lineChart: LineChart,
    entries: List<Entry>,
    listener: OnChartValueSelectedListener
  ) {
    val labels = ArrayList<String>()
    labels.add("일")
    labels.add("월")
    labels.add("화")
    labels.add("수")
    labels.add("목")
    labels.add("금")
    labels.add("토")

    val dataSet = LineDataSet(entries, "마신 물의 양")
    val data = LineData(labels, dataSet)
    lineChart.data = data
    lineChart.setOnChartValueSelectedListener(listener)

    val computed = intArrayOf(Color.TRANSPARENT)
    val label = arrayOf("")
    lineChart.setDescription("")
    lineChart.setDescriptionTextSize(16f)
    lineChart.setDescriptionColor(Color.WHITE)
    lineChart.legend.isEnabled = true
    lineChart.legend.isWordWrapEnabled = true
    lineChart.legend.textColor = Color.TRANSPARENT
    lineChart.legend.setCustom(computed, label)

    lineChart.setDrawGridBackground(false)
    lineChart.axisLeft.setDrawGridLines(false)
    lineChart.axisRight.setDrawGridLines(false)
    lineChart.axisRight.textColor = Color.TRANSPARENT
    lineChart.xAxis.setDrawGridLines(false)

    lineChart.setPinchZoom(false)
    lineChart.isDragEnabled = false
    lineChart.setScaleEnabled(false)
    lineChart.animateY(1700)

    val mv = LineChartMarkerView(context, R.layout.layout_line_chart_marker)
    lineChart.markerView = mv

    // X - axis settings
    val xAxis = lineChart.xAxis
    xAxis.textSize = 14f
    xAxis.spaceBetweenLabels = 1
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.textColor = ColorTemplate.getHoloBlue()

    // Y - axis settings
    val leftAxis = lineChart.axisLeft
    leftAxis.textColor = ColorTemplate.getHoloBlue()
    leftAxis.textSize = 14f
    leftAxis.setStartAtZero(true)
    leftAxis.spaceTop = 45f
    leftAxis.valueFormatter = LineChartYAxisFormatter()
  }
}
