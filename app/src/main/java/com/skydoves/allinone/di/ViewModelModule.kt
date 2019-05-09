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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.factory.AppViewModelFactory
import com.skydoves.allinone.view.ui.main.MainActivityViewModel
import com.skydoves.allinone.view.ui.outdoor.OutDoorViewModel
import com.skydoves.allinone.view.ui.setting.water.WaterGoalViewModel
import com.skydoves.allinone.view.ui.todo.TodoViewModel
import com.skydoves.allinone.view.ui.waterdrink.WaterDrinkViewModel
import com.skydoves.allinone.view.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(WaterGoalViewModel::class)
  internal abstract fun bindWaterGoalActivityViewModel(waterGoalViewModel: WaterGoalViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(WaterDrinkViewModel::class)
  internal abstract fun bindWaterDrinkViewModel(waterDrinkViewModel: WaterDrinkViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(OutDoorViewModel::class)
  internal abstract fun bindOutDoorViewModel(outDoorViewModel: OutDoorViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(TodoViewModel::class)
  internal abstract fun bindTodoViewModel(todoViewModel: TodoViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(WeatherViewModel::class)
  internal abstract fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
