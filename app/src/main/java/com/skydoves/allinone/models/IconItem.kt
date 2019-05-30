package com.skydoves.allinone.models

import android.graphics.drawable.Drawable

data class IconItem(
  val resource: Drawable?,
  var isChecked: Boolean = false
)