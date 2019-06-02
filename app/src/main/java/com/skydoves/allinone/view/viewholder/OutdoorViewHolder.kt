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

import android.view.View
import androidx.core.content.ContextCompat
import com.skydoves.allinone.models.OutDoor
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_outdoor.view.*

class OutdoorViewHolder(
  private val delegate: Delegate,
  view: View
) : BaseViewHolder(view) {

  interface Delegate {
    fun onItemClick(outDoor: OutDoor)
  }

  private lateinit var outdoor: OutDoor

  override fun bindData(data: Any) {
    if (data is OutDoor) {
      this.outdoor = data
      drawItemUI()
    }
  }

  private fun drawItemUI() {
    itemView.run {
      cover.background = ContextCompat.getDrawable(context, outdoor.background)
      outdoor.title?.let { title.text = it }
    }
  }

  override fun onClick(p0: View?) {
    delegate.onItemClick(outdoor)
  }

  override fun onLongClick(p0: View?) = false
}
