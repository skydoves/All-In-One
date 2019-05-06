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

package com.skydoves.allinone.view.ui.waterdrink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.skydoves.allinone.models.entities.WaterDrink
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.persistence.room.dao.WaterDrinkDao
import com.skydoves.allinone.utils.AbsentLiveData
import com.skydoves.allinone.utils.WaterDrinkItemUtils
import org.threeten.bp.OffsetDateTime
import timber.log.Timber
import javax.inject.Inject

class WaterDrinkViewModel @Inject
constructor(private val waterDrinkDao: WaterDrinkDao) : ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  private val waterDrinkLiveData: MutableLiveData<WaterDrink> = MutableLiveData()
  private val waterDrinks: LiveData<List<WaterDrink>>

  init {
    Timber.d("injection WaterDrinkViewModel")

    waterDrinkLiveData.value = WaterDrinkItemUtils.getDummyWaterDrink()
    waterDrinks = Transformations.switchMap(waterDrinkLiveData) {
      waterDrinkLiveData.value?.let {
        waterDrinkDao.getWaterDrinksByDate(WaterDrinkItemUtils.getDateString(OffsetDateTime.now())) }
          ?: AbsentLiveData.create()
    }
  }

  fun getWaterGoal() = setting.waterGoal

  fun getTodayWaterDrinks() = waterDrinks

  fun getWaterDrinksByDate(date: String) = waterDrinkDao.getWaterDrinksByDate(date)

  fun getWaterDrinksFromDate(date: String) = waterDrinkDao.getWaterDrinksFromToday(date)

  fun insertWaterDrink(waterDrink: WaterDrink) {
    waterDrink.timeStamp = OffsetDateTime.now()
    waterDrinkDao.insertWaterDrink(waterDrink)
    waterDrinkLiveData.postValue(waterDrink)
  }
}
