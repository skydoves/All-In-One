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

import androidx.lifecycle.ViewModel
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import timber.log.Timber
import javax.inject.Inject

class WaterGoalViewModel @Inject
constructor() : ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  init {
    Timber.d("injection WaterGoalViewModel")
  }

  fun getWaterGoal(): Int = setting.waterGoal

  fun setWaterGoal(goal: Int) = setting.putWaterGoal(goal)
}
