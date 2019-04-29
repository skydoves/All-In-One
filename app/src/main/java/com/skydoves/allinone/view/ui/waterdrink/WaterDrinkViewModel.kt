package com.skydoves.allinone.view.ui.waterdrink

import androidx.lifecycle.ViewModel
import com.skydoves.allinone.models.entities.WaterDrink
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.persistence.room.dao.WaterDrinkDao
import timber.log.Timber
import javax.inject.Inject

class WaterDrinkViewModel @Inject
constructor(private val waterDrinkDao: WaterDrinkDao): ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  init {
    Timber.d("injection WaterDrinkViewModel")
  }

  fun getWaterGoal(): Int {
    return setting.waterGoal
  }

  fun insertWaterDrink(waterDrink: WaterDrink) {
    waterDrinkDao.insertWaterDrink(waterDrink)
  }
}