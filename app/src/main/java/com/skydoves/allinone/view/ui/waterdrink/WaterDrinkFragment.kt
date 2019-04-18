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

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.jorgecastillo.FillableLoaderBuilder
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform
import com.skydoves.allinone.R
import com.skydoves.allinone.utils.FillAbleLoaderPaths
import com.skydoves.allinone.utils.FillAbleLoaderUtils
import com.skydoves.allinone.view.ui.main.MainActivityViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_waterdrink.*
import javax.inject.Inject

class WaterDrinkFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: MainActivityViewModel

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.layout_waterdrink, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    context?.let {
      val loaderBuilder = FillableLoaderBuilder()
      val fillAbleLoader = loaderBuilder.parentView(parentView)
          .svgPath(FillAbleLoaderPaths.SVG_WATERDROP)
          .layoutParams(FillAbleLoaderUtils.getParams(it))
          .originalDimensions(290, 425)
          .fillColor(ContextCompat.getColor(it, R.color.waterBlue))
          .strokeColor(ContextCompat.getColor(it, R.color.waterBlue))
          .strokeDrawingDuration(0)
          .clippingTransform(WavesClippingTransform())
          .fillDuration(3000)
          .build()

      fillAbleLoader.setSvgPath(FillAbleLoaderPaths.SVG_WATERDROP)
      fillAbleLoader.setFillColor(Color.WHITE)
      fillAbleLoader.setPercentage(70f)
      fillAbleLoader.start()
    }
  }
}
