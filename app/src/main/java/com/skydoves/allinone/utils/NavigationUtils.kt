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

package com.skydoves.allinone.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.skydoves.allinone.R
import devlight.io.library.ntb.NavigationTabBar

object NavigationUtils {

  fun getNavigationModels(mContext: Context): ArrayList<NavigationTabBar.Model> {
    val colors = mContext.resources.getStringArray(R.array.default_preview)
    val models = ArrayList<NavigationTabBar.Model>()
    models.add(
      NavigationTabBar.Model.Builder(
        ContextCompat.getDrawable(mContext, R.drawable.ic_outdoor),
        Color.parseColor(colors[0])
      )
        .title("야외 활동")
        .badgeTitle("new")
        .build()
    )
    models.add(
      NavigationTabBar.Model.Builder(
        ContextCompat.getDrawable(mContext, R.drawable.ic_drop),
        Color.parseColor(colors[1])
      )
        .title("수분 섭취")
        .badgeTitle("new")
        .build()
    )
    models.add(
      NavigationTabBar.Model.Builder(
        ContextCompat.getDrawable(mContext, R.drawable.ic_todo),
        Color.parseColor(colors[2])
      )
        .title("할일 목록")
        .badgeTitle("new")
        .build()
    )
    models.add(
      NavigationTabBar.Model.Builder(
        ContextCompat.getDrawable(mContext, R.drawable.ic_sunny),
        Color.parseColor(colors[3])
      )
        .title("오늘 날씨")
        .badgeTitle("new")
        .build()
    )
    models.add(
      NavigationTabBar.Model.Builder(
        ContextCompat.getDrawable(mContext, R.drawable.ic_setting),
        Color.parseColor(colors[4])
      )
        .title("환경 설정")
        .badgeTitle("new")
        .build()
    )
    return models
  }

  fun setComponents(context: Context, viewPager: ViewPager, navigationTabBar: NavigationTabBar) {
    navigationTabBar.models = NavigationUtils.getNavigationModels(context)
    navigationTabBar.setViewPager(viewPager, 2)
    navigationTabBar.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

      override fun onPageSelected(position: Int) {
        navigationTabBar.models[position].hideBadge()
      }

      override fun onPageScrollStateChanged(state: Int) {}
    })
  }
}
