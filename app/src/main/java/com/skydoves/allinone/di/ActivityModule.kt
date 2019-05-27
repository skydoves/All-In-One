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

package com.skydoves.allinone.di

import com.skydoves.allinone.view.ui.main.MainActivity
import com.skydoves.allinone.view.ui.setting.local.LocalActivity
import com.skydoves.allinone.view.ui.setting.water.WaterGoalActivity
import com.skydoves.allinone.view.ui.todo.AddTodoActivity
import com.skydoves.allinone.view.ui.waterdrink.WaterDrinkSelectActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

  @ContributesAndroidInjector
  internal abstract fun contributeWaterGoalActivity(): WaterGoalActivity

  @ContributesAndroidInjector
  internal abstract fun contributeWaterDrinkSelectActivity(): WaterDrinkSelectActivity

  @ContributesAndroidInjector
  internal abstract fun contributeLocalActivity(): LocalActivity

  @ContributesAndroidInjector
  internal abstract fun contributeAddTodoActivity(): AddTodoActivity
}
