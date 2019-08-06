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
import com.skydoves.allinone.R
import org.threeten.bp.OffsetDateTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

  fun getIndexOfDayName(context: Context, index: Int): String {
    return when (index) {
      1 -> context.getString(R.string.label_monday)
      2 -> context.getString(R.string.label_tuesday)
      3 -> context.getString(R.string.label_wednesday)
      4 -> context.getString(R.string.label_thursday)
      5 -> context.getString(R.string.label_friday)
      6 -> context.getString(R.string.label_saturday)
      else -> context.getString(R.string.label_sunday)
    }
  }

  fun getFarDay(far: Int): String {
    val date = Date()
    val cal = Calendar.getInstance()
    cal.time = date
    cal.add(Calendar.DAY_OF_MONTH, far)
    val sdf = SimpleDateFormat("yyyy-M-d", Locale.KOREA)
    return sdf.format(cal.time)
  }

  fun getDateDay(): Int {
    try {
      val dateFormat = SimpleDateFormat("yyyy-M-d", Locale.KOREA)
      val nDate = dateFormat.parse(getFarDay(0))
      val cal = Calendar.getInstance()
      nDate?.let { cal.time = it }
      return cal.get(Calendar.DAY_OF_WEEK) - 1
    } catch (e: ParseException) {
      e.printStackTrace()
    }
    return 0
  }

  fun getWeeklyBoundDateTime(): String {
    val time = OffsetDateTime.now().minusDays(getDateDay().toLong())
      .withHour(0)
      .withMinute(0)
      .withSecond(0)
      .withNano(0)
    return time.toString()
  }

  fun getSinceToday(): String {
    val time = OffsetDateTime.now()
      .withHour(0)
      .withMinute(0)
      .withSecond(0)
      .withNano(0)
    return time.toString()
  }
}
