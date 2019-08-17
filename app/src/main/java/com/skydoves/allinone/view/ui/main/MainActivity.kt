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

package com.skydoves.allinone.view.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.R
import com.skydoves.allinone.factory.NeedsFactory
import com.skydoves.allinone.utils.NavigationUtils
import com.skydoves.allinone.view.adapter.viewpager.MainPagerAdapter
import com.skydoves.allinone.view.ui.intro.AppIntroActivity
import com.skydoves.needs.OnConfirmListener
import com.skydoves.needs.needs
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }
  private val need by needs(NeedsFactory::class)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initializeUI()
    showIntro()
  }

  private fun initializeUI() {
    viewpager.adapter = MainPagerAdapter(supportFragmentManager)
    viewpager.offscreenPageLimit = 5
    NavigationUtils.setComponents(this, viewpager, navigation)
    configureNeeds()
  }

  private fun showIntro() {
    viewModel.showIntro {
      startActivity(Intent(this, AppIntroActivity::class.java))
    }
  }

  private fun configureNeeds() {
    this.need.setOnConfirmListener(object : OnConfirmListener {
      override fun onConfirm() {
        toast(getString(R.string.needs_confirm))
        need.dismiss()
      }
    })
    viewpager.viewTreeObserver.addOnGlobalLayoutListener {
      need.show(viewpager)
    }
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return fragmentInjector
  }
}
