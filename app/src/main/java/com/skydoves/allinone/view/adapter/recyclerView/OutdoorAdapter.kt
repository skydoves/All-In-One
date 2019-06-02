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
import com.skydoves.allinone.models.OutDoor
import com.skydoves.allinone.view.viewholder.OutdoorViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class OutdoorAdapter(
  val delegate: OutdoorViewHolder.Delegate
)
  : BaseAdapter() {

  init {
    addSection(ArrayList<OutDoor>())

    addItemOnSection(0, OutDoor(R.drawable.bg_morning, "스트레칭"))
    addItemOnSection(0, OutDoor(R.drawable.bg_afternoon, "눈 운동"))
    addItemOnSection(0, OutDoor(R.drawable.bg_night, "명상"))
    notifyDataSetChanged()
  }

  override fun layout(sectionRow: SectionRow) = R.layout.item_outdoor

  override fun viewHolder(layout: Int, view: View) = OutdoorViewHolder(delegate, view)
}
