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

import com.skydoves.allinone.R
import com.skydoves.allinone.models.entities.WaterDrink
import org.threeten.bp.OffsetDateTime

object WaterDrinkItemUtils {

  fun getWaterDrinkIcon(amount: Int): Int {
    return when {
      amount <= 125 -> R.drawable.ic_glass0
      amount <= 250 -> R.drawable.ic_glass1
      amount <= 350 -> R.drawable.ic_glass2
      amount <= 500 -> R.drawable.ic_glass3
      else -> R.drawable.ic_glass4
    }
  }

  fun getDateString(time: OffsetDateTime): String {
    return "${time.year}${time.dayOfMonth}${time.dayOfMonth}"
  }

  fun getDefaultModels(): List<WaterDrink> {
    val time = OffsetDateTime.now()
    val items: MutableList<WaterDrink> = ArrayList()
    items.add(WaterDrink(time, getDateString(time), getWaterDrinkIcon(125), 125))
    items.add(WaterDrink(time, getDateString(time), getWaterDrinkIcon(250), 250))
    items.add(WaterDrink(time, getDateString(time), getWaterDrinkIcon(350), 350))
    items.add(WaterDrink(time, getDateString(time), getWaterDrinkIcon(500), 500))
    items.add(WaterDrink(time, getDateString(time), getWaterDrinkIcon(1000), 1000))
    return items
  }
}
