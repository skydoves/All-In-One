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

import com.github.mikephil.charting.data.Entry
import com.skydoves.allinone.R
import com.skydoves.allinone.models.Weather

object LocalUtils {

  val locals = arrayOf(
    "서울특별시", "경기도", "강원도",
    "경상남도", "경상북도", "광주광역시",
    "대구광역시", "대전광역시", "부산광역시",
    "울산광역시", "인천광역시", "전라남도",
    "전라북도", "충청북도", "충청남도",
    "제주특별자치도")

  fun getLocalName(index: Int): String {
    return locals[index]
  }

  fun getLocalUrl(index: Int): String {
    when (index) {
      1 -> return "4182025000"
      2 -> return "4282025000"
      3 -> return "4817074000"
      4 -> return "4729053000"
      5 -> return "2920054000"
      6 -> return "2720065000"
      7 -> return "3023052000"
      8 -> return "2644058000"
      9 -> return "3114056000"
      10 -> return "2871025000"
      11 -> return "4681025000"
      12 -> return "4579031000"
      13 -> return "4376031000"
      14 -> return "4376031000"
      15 -> return "5013025300"
      else -> return "1159068000"
    }
  }

  fun getWeatherIcon(wfKor: String): Int {
    return when {
      wfKor.contains("구름 조금") -> R.drawable.ic_sunny_cloud
      wfKor.contains("구름 많음") -> R.drawable.ic_cloudy
      wfKor.contains("비") -> R.drawable.ic_rainy
      wfKor.contains("눈") -> R.drawable.ic_snowy
      else -> R.drawable.ic_sunny_day
    }
  }

  fun getWeatherNewLabels(weathers: List<Weather>): List<String> {
    val labels = ArrayList<String>()
    for (weather in weathers) {
      labels.add("${weather.hour}시")
      if (weathers.indexOf(weather) == 5) break
    }
    return labels
  }

  fun getWeatherNewDegrees(weathers: List<Weather>): List<Entry> {
    val entries = ArrayList<Entry>()
    for (weather in weathers) {
      entries.add(Entry(weather.temp.toInt().toFloat(), weathers.indexOf(weather)))
      if (weathers.indexOf(weather) == 5) break
    }
    return entries
  }
}
