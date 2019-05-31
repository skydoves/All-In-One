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

import android.view.View
import com.skydoves.allinone.R
import com.skydoves.allinone.models.IconItem
import com.skydoves.allinone.view.viewholder.TodoIconViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class TodoIconAdapter(private val delegate: TodoIconViewHolder.Delegate) : BaseAdapter() {

  init {
    addSection(ArrayList<IconItem>())

    addItemOnSection(0, IconItem(R.drawable.ic_date, false))
    addItemOnSection(0, IconItem( R.drawable.ic_people, false))
    addItemOnSection(0, IconItem(R.drawable.ic_work, false))
    addItemOnSection(0, IconItem(R.drawable.ic_location, false))
    addItemOnSection(0, IconItem(R.drawable.ic_drop, false))
    addItemOnSection(0, IconItem(R.drawable.ic_outdoor, false))
    addItemOnSection(0, IconItem(R.drawable.ic_run, false))
    addItemOnSection(0, IconItem(R.drawable.ic_bike, false))
    addItemOnSection(0, IconItem(R.drawable.ic_drive, false))
    notifyDataSetChanged()
  }

  fun checkIconItem(iconItem: IconItem) {
    for (item in sections()[0]) {
      item as IconItem
      item.isChecked = false
    }
    (sections()[0][sections()[0].indexOf(iconItem)] as IconItem).isChecked = true
    notifyDataSetChanged()
  }

  fun getFirstItem(): IconItem {
    return sections()[0][0] as IconItem
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_icon

  override fun viewHolder(layout: Int, view: View) = TodoIconViewHolder(delegate, view)
}
