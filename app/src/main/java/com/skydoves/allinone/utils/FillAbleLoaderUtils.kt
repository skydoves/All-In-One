package com.skydoves.allinone.utils

import android.content.Context
import android.util.TypedValue
import android.widget.RelativeLayout
import com.skydoves.allinone.R

object FillAbleLoaderUtils {
  fun getParams(context: Context): RelativeLayout.LayoutParams {
    val viewSize = context.resources.getDimensionPixelSize(R.dimen.fourthSampleViewSize)
    val params = RelativeLayout.LayoutParams(viewSize, viewSize)
    params.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220f, context.resources.displayMetrics).toInt()
    params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 305f, context.resources.displayMetrics).toInt()
    params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
    return params
  }
}