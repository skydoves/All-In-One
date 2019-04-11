package com.skydoves.allinone.view.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro

class AppIntroActivity: AppIntro() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onSkipPressed(currentFragment: Fragment) {
    super.onSkipPressed(currentFragment)
    finish()
  }

  override fun onDonePressed(currentFragment: Fragment) {
    super.onDonePressed(currentFragment)
    finish()
  }
}