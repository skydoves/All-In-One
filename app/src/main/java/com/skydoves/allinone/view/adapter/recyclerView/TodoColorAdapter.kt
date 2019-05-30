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

package com.skydoves.allinone.view.adapter.recyclerView

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.skydoves.allinone.R
import com.skydoves.allinone.models.ColorItem
import com.skydoves.allinone.view.viewholder.TodoColorViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class TodoColorAdapter(
  context: Context,
  private val delegate: TodoColorViewHolder.Delegate
) : BaseAdapter() {

  init {
    addSection(ArrayList<ColorItem>())

    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.waterBlue), false))
    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.colorPrimary), false))
    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.yellow), false))
    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.warm_pink), false))
    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.purple), false))
    addItemOnSection(0, ColorItem(ContextCompat.getColor(context, R.color.black), false))
    notifyDataSetChanged()
  }

  fun checkColorItem(colorItem: ColorItem) {
    for (item in sections()[0]) {
      item as ColorItem
      item.isChecked = false
    }
    (sections()[0][sections()[0].indexOf(colorItem)] as ColorItem).isChecked = true
    notifyDataSetChanged()
  }

  fun getFirstItem(): ColorItem {
    return sections()[0][0] as ColorItem
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_color

  override fun viewHolder(layout: Int, view: View) = TodoColorViewHolder(delegate, view)
}
