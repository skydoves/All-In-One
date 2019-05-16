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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.observeLiveData
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.utils.LineChartUtils
import com.skydoves.allinone.utils.LocalUtils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_weather.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject

class WeatherFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, WeatherViewModel::class) }

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.layout_weather, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    context?.let {
      layout_weather.backgroundDrawable = ContextCompat.getDrawable(it, LocalUtils.getTimeFlowBackground())
    }
    local.text = LocalUtils.getLocalName(viewModel.getLocal())
    observeLiveData()
  }

  private fun observeLiveData() {
    observeLiveData(viewModel.weatherLiveData()) {
      if (it.isNotEmpty()) {
        context?.let { context ->
          val weather = it[0]
          status.text = weather.wfKor
          icon_weather.setImageDrawable(ContextCompat.getDrawable(context, LocalUtils.getWeatherIcon(weather.wfKor)))
          degree.text = weather.temp.toInt().toString()
          LineChartUtils.setWeatherLineChart(lineChart, LocalUtils.getWeatherNewLabels(it), LocalUtils.getWeatherNewDegrees(it))
        }
      }
    }
    observeLiveData(viewModel.airLiveData()) {
      Timber.d(it.toString())
    }
    observeLiveData(viewModel.toastLiveData()) {
      toast(it)
    }
  }
}
