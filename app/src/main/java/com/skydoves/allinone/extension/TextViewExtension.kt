package com.skydoves.allinone.extension

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.skydoves.allinone.R
import org.jetbrains.anko.textColor

fun TextView.applyPm10Color(value: Int) {
  when {
    value < 80 -> this.textColor = ContextCompat.getColor(this.context, R.color.green)
    value < 150 -> this.textColor = ContextCompat.getColor(this.context, R.color.yellow)
    else -> this.textColor = ContextCompat.getColor(this.context, R.color.red)
  }
}

fun TextView.applyPm25Color(value: Int) {
  when {
    value < 35 -> this.textColor = ContextCompat.getColor(this.context, R.color.green)
    value < 75 -> this.textColor = ContextCompat.getColor(this.context, R.color.yellow)
    else -> this.textColor = ContextCompat.getColor(this.context, R.color.red)
  }
}