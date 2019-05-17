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

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.skydoves.allinone.R
import org.jetbrains.anko.textColor

fun TextView.applyPm10Color(value: Int) {
  when {
    value < 80 -> this.textColor = ContextCompat.getColor(this.context, R.color.white)
    value < 150 -> this.textColor = ContextCompat.getColor(this.context, R.color.yellow)
    else -> this.textColor = ContextCompat.getColor(this.context, R.color.red)
  }
}

fun TextView.applyPm25Color(value: Int) {
  when {
    value < 35 -> this.textColor = ContextCompat.getColor(this.context, R.color.white)
    value < 75 -> this.textColor = ContextCompat.getColor(this.context, R.color.yellow)
    else -> this.textColor = ContextCompat.getColor(this.context, R.color.red)
  }
}
