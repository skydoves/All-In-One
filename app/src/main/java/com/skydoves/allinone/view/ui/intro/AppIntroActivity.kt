package com.skydoves.allinone.view.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.skydoves.allinone.R

class AppIntroActivity: AppIntro() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    addSlide(IntroFragment.newInstance(R.layout.layout_intro01))
    addSlide(IntroFragment.newInstance(R.layout.layout_intro02))
    addSlide(IntroFragment.newInstance(R.layout.layout_intro02))
    addSlide(IntroFragment.newInstance(R.layout.layout_intro02))
    setDoneText(getString(R.string.label_start))
  }

  override fun onSkipPressed(currentFragment: Fragment) {
    super.onSkipPressed(currentFragment)
    finish()
  }

  override fun onDonePressed(currentFragment: Fragment) {
    super.onDonePressed(currentFragment)
    finish()
  }

  override fun onBackPressed() = Unit
}