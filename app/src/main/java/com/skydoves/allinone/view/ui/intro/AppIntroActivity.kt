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

package com.skydoves.allinone.view.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.skydoves.allinone.R
import com.skydoves.allinone.view.ui.setting.local.LocalActivity
import com.skydoves.allinone.view.ui.setting.water.WaterGoalActivity
import org.jetbrains.anko.startActivity

class AppIntroActivity : AppIntro() {

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
    doneOrSkip()
  }

  override fun onDonePressed(currentFragment: Fragment) {
    super.onDonePressed(currentFragment)
    doneOrSkip()
  }

  private fun doneOrSkip() {
    startActivity<LocalActivity>()
    startActivity<WaterGoalActivity>()
    finish()
  }

  override fun onBackPressed() = Unit
}
