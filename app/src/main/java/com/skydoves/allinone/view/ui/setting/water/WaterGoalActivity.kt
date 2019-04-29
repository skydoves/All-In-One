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

package com.skydoves.allinone.view.ui.setting.water

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.vm
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_water_goal.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class WaterGoalActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, WaterGoalViewModel::class)}

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_water_goal)
    initializeUI()
  }

  private fun initializeUI() {
    waterGaol.setText(viewModel.getWaterGoal().toString())
    waterGaol_confirm.setOnClickListener {
      val goal = waterGaol.text.toString()
      when (validate(goal)) {
        true -> {
          viewModel.setWaterGoal(goal.toInt())
          toast(getString(R.string.toast_setGoal))
          finish()
        }
        false -> toast(getString(R.string.toast_wrong_goal))
      }
    }
  }

  private fun validate(text: String): Boolean {
    return !(text.isEmpty() || text == "0")
  }
}
