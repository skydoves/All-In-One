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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.observeLiveData
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.entities.WaterDrink
import com.skydoves.allinone.utils.WaterDrinkItemUtils
import com.skydoves.allinone.view.adapter.recyclerView.WaterItemAdapter
import com.skydoves.allinone.view.viewholder.WaterItemViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_item_select_popup.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class WaterDrinkSelectActivity : AppCompatActivity(), WaterItemViewHolder.Delegate {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, WaterDrinkViewModel::class) }

  private var amount = -1
  private val adapter by lazy { WaterItemAdapter(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.layout_item_select_popup)
    initializeUI()
    observeLiveData()
  }

  private fun initializeUI() {
    recyclerView.adapter = adapter
    recyclerView.layoutManager = GridLayoutManager(this, 3)
    adapter.addItems(WaterDrinkItemUtils.getDefaultModels())
    cancel.setOnClickListener { finish() }
  }

  private fun observeLiveData() {
    observeLiveData(viewModel.getTodayWaterDrinks()) {
      if (amount != -1) {
        toast("$amount${getString(R.string.toast_drink_water)}")
        finish()
      }
    }
  }

  override fun onItemClick(waterDrink: WaterDrink) {
    viewModel.takeWaterDrink(this, waterDrink)
    amount = waterDrink.amount
    recyclerView.isEnabled = false
  }
}
