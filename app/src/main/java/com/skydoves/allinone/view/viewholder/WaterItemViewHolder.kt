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

package com.skydoves.allinone.view.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import com.skydoves.allinone.models.entities.WaterDrink
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_waterdrink.view.*

class WaterItemViewHolder(
  private val delegate: Delegate,
  private val view: View
) : BaseViewHolder(view) {

  private lateinit var waterDrink: WaterDrink

  interface Delegate {
    fun onItemClick(waterDrink: WaterDrink)
  }

  override fun bindData(data: Any) {
    if (data is WaterDrink) {
      this.waterDrink = data
      drawItemUI()
    }
  }

  @SuppressLint("SetTextI18n")
  private fun drawItemUI() {
    itemView.apply {
      icon.setImageDrawable(ContextCompat.getDrawable(context, waterDrink.icon))
      amount.text = "${waterDrink.amount}ml"
    }
  }

  override fun onClick(p0: View?) {
    this.delegate.onItemClick(waterDrink)
  }

  override fun onLongClick(p0: View?) = false
}
