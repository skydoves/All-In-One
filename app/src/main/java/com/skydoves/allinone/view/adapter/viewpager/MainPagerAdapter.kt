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

package com.skydoves.allinone.view.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skydoves.allinone.view.ui.outdoor.OutdoorFragment
import com.skydoves.allinone.view.ui.setting.SettingFragment
import com.skydoves.allinone.view.ui.todo.TodoFragment
import com.skydoves.allinone.view.ui.waterdrink.WaterDrinkFragment
import com.skydoves.allinone.view.ui.weather.WeatherFragment

class MainPagerAdapter(fm: FragmentManager)
  : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> OutdoorFragment()
      1 -> WaterDrinkFragment()
      2 -> TodoFragment()
      3 -> WeatherFragment()
      else -> SettingFragment()
    }
  }

  override fun getCount() = 5
}
