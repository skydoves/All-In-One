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

package com.skydoves.allinone.view.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class IntroFragment : Fragment() {
  private var layoutResId: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null && arguments?.containsKey(ARG_LAYOUT_RES_ID)!!) {
      layoutResId = arguments?.getInt(ARG_LAYOUT_RES_ID)!!
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutResId, container, false)
  }

  companion object {
    private const val ARG_LAYOUT_RES_ID = "layoutResId"

    fun newInstance(layoutResId: Int): IntroFragment {
      val sampleSlide = IntroFragment()
      val args = Bundle()
      args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
      sampleSlide.arguments = args
      return sampleSlide
    }
  }
}
