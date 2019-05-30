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

import android.graphics.PorterDuff
import android.view.View
import com.skydoves.allinone.models.ColorItem
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_color.view.*

class TodoColorViewHolder(
  val delegate: Delegate,
  val view: View
) : BaseViewHolder(view) {

  interface Delegate {
    fun onColorItemClick(color: ColorItem)
  }

  private lateinit var colorItem: ColorItem

  override fun bindData(data: Any) {
    if (data is ColorItem) {
      this.colorItem = data
      updateUI()
    }
  }

  private fun updateUI() {
    itemView.run {
      item_color.drawable.setColorFilter(colorItem.color, PorterDuff.Mode.SRC_OVER)
    }
  }

  override fun onClick(p0: View?) {
    this.delegate.onColorItemClick(colorItem)
  }

  override fun onLongClick(p0: View?) = false
}
