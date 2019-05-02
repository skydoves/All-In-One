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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skydoves.allinone.models.entities.WaterDrink
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.persistence.room.dao.WaterDrinkDao
import com.skydoves.allinone.utils.WaterDrinkItemUtils
import org.threeten.bp.OffsetDateTime
import timber.log.Timber
import javax.inject.Inject

class WaterDrinkViewModel @Inject
constructor(private val waterDrinkDao: WaterDrinkDao) : ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  val waterDrinks: MutableLiveData<List<WaterDrink>> = MutableLiveData()

  init {
    Timber.d("injection WaterDrinkViewModel")
  }

  fun getWaterGoal(): Int {
    return setting.waterGoal
  }

  fun insertWaterDrink(waterDrink: WaterDrink) {
    waterDrink.timeStamp = OffsetDateTime.now()
    waterDrinkDao.insertWaterDrink(waterDrink)
  }

  fun getWaterDrinkByDate(date: OffsetDateTime) {
    waterDrinkDao.getWaterDrinksByDate(WaterDrinkItemUtils.getDateString(date)).observeForever {
      waterDrinks.postValue(it)
    }
  }
}
