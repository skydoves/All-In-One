package com.skydoves.allinone.extension

import android.os.Build
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.allinone.R

fun RecyclerView.slideFromRightAnimation() {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    val controller: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.recyclerview_slide_right_animation)
    this.layoutAnimation = controller
    this.scheduleLayoutAnimation()
  }
}