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

package com.skydoves.allinone.view.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.skydoves.allinone.api.ApiResponse
import com.skydoves.allinone.api.client.KMAClient
import com.skydoves.allinone.models.Weather
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.utils.AbsentLiveData
import com.skydoves.allinone.utils.LocalUtils
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject
constructor(private val kmaClient: KMAClient) : ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()
  private val weatherLiveData: MutableLiveData<Int> = MutableLiveData()
  private val weathers: LiveData<List<Weather>>
  private val toast: MutableLiveData<String> = MutableLiveData()

  init {
    Timber.d("injection WeatherViewModel")

    weatherLiveData.value = getLocal()
    weathers = weatherLiveData.switchMap {
      weatherLiveData.value?.let { fetchWeatherList(it) } ?: AbsentLiveData.create()
    }
  }

  private fun fetchWeatherList(local: Int): LiveData<List<Weather>> {
    val weathersLiveData: MutableLiveData<List<Weather>> = MutableLiveData()
    kmaClient.fetchWeather(LocalUtils.getLocalUrl(local)) {
      when (it) {
        is ApiResponse.Success ->
          weathersLiveData.postValue(it.data?.getWeatherList())
        is ApiResponse.Failure.Error ->
          toast.postValue("${it.code}: ${it.responseBody?.string()}")
        is ApiResponse.Failure.Exception ->
          toast.postValue("${it.message}")
      }
    }
    return weathersLiveData
  }

  fun weatherLiveData() = weathers

  fun toastLiveData() = toast

  fun getLocal() = setting.local
}
